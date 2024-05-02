/*
 * Copyright (C) 2011 readyState Software Ltd, 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package droidrocks.com.namaztimeapp.SQLiteAssetHelperLibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * A helper class to manage database creation and version management using 
 * an application's raw asset files.
 *
 * This class provides developers with a simple way to ship their Android app 
 * with an existing SQLite database (which may be pre-populated with data) and 
 * to manage its initial creation and any upgrades required with subsequent 
 * version releases.
 *
 * <p>This class makes it easy for {@link android.content.ContentProvider}
 * implementations to defer opening and upgrading the database until first use,
 * to avoid blocking application startup with long-running database upgrades.
 *
 * <p>For examples see <a href="https://github.com/jgilfelt/android-sqlite-asset-helper">
 * https://github.com/jgilfelt/android-sqlite-asset-helper</a>
 *
 * <p class="note"><strong>Note:</strong> this class assumes
 * monotonically increasing version numbers for upgrades.  Also, there
 * is no concept of a database downgrade; installing a new version of
 * your app which uses a lower version number than a
 * previously-installed version will result in undefined behavior.</p>
 */
public class SQLiteAssetHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLiteAssetHelper.class.getSimpleName();
    private static final String ASSET_DB_PATH = "databases";

    private final Context mContext;
    private final String mName;
    private final CursorFactory mFactory;
    private final int mNewVersion;

    private SQLiteDatabase mDatabase = null;
    private boolean mIsInitializing = false;

    private String mDatabasePath;

    private String mAssetPath;

    private String mUpgradePathFormat;

    private int mForcedUpgradeVersion = 0;

    /**
     * Create a helper object to create, open, and/or manage a database in 
     * a specified location.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name of the database file
     * @param storageDirectory to store the database file upon creation; caller must
     *     ensure that the specified absolute path is available and can be written to  
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *     SQL file(s) contained within the application assets folder will be used to 
     *     upgrade the database
     */
    public SQLiteAssetHelper(Context context, String name, String storageDirectory, CursorFactory factory, int version) {
        super(context, name, factory, version);

        if (version < 1) throw new IllegalArgumentException("Version must be >= 1, was " + version);
        if (name == null) throw new IllegalArgumentException("Database name cannot be null");

        mContext = context;
        mName = name;
        mFactory = factory;
        mNewVersion = version;

        mAssetPath = ASSET_DB_PATH + "/" + name;
        if (storageDirectory != null) {
            mDatabasePath = storageDirectory;
        } else {
            mDatabasePath = context.getApplicationInfo().dataDir + "/databases";
        }
        mUpgradePathFormat = ASSET_DB_PATH + "/" + name + "_upgrade_%s-%s.sql";
    }

    /**
     * Create a helper object to create, open, and/or manage a database in 
     * the application's default private data directory.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name of the database file
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *     SQL file(s) contained within the application assets folder will be used to 
     *     upgrade the database
     */
    public SQLiteAssetHelper(Context context, String name, CursorFactory factory, int version) {
        this(context, name, null, factory, version);
    }

    /**
     * Create and/or open a database that will be used for reading and writing.
     * The first time this is called, the database will be extracted and copied
     * from the application's assets folder.
     *
     * <p>Once opened successfully, the database is cached, so you can
     * call this method every time you need to write to the database.
     * (Make sure to call {@link #close} when you no longer need the database.)
     * Errors such as bad permissions or a full disk may cause this method
     * to fail, but future attempts may succeed if the problem is fixed.</p>
     *
     * <p class="caution">Database upgrade may take a long time, you
     * should not call this method from the application main thread, including
     * from {@link android.content.ContentProvider#onCreate ContentProvider.onCreate()}.
     *
     * @throws SQLiteException if the database cannot be opened for writing
     * @return a read/write database object valid until {@link #close} is called
     */
    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (mDatabase != null && mDatabase.isOpen() && !mDatabase.isReadOnly()) {
            return mDatabase;  // The database is already open for business
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        }

        // If we have a read-only database open, someone could be using it
        // (though they shouldn't), which would cause a lock to be held on
        // the file, and our attempts to open the database read-write would
        // fail waiting for the file lock.  To prevent that, we acquire the
        // lock on the read-only database, which shuts out other users.

        boolean success = false;
        SQLiteDatabase db = null;
        //if (mDatabase != null) mDatabase.lock();
        try {
            mIsInitializing = true;
            //if (mName == null) {
            //    db = SQLiteDatabase.create(null);
            //} else {
            //    db = mContext.openOrCreateDatabase(mName, 0, mFactory);
            //}
            db = createOrOpenDatabase(false);

            int version = db.getVersion();

            // do force upgrade
            if (version != 0 && version < mForcedUpgradeVersion) {
                db = createOrOpenDatabase(true);
                db.setVersion(mNewVersion);
                version = db.getVersion();
            }

            if (version != mNewVersion) {
                db.beginTransaction();
                try {
                    if (version == 0) {
                        onCreate(db);
                    } else {
                        if (version > mNewVersion) {
                            Log.w(TAG, "Can't downgrade read-only database from version " +
                                    version + " to " + mNewVersion + ": " + db.getPath());
                        }
                        onUpgrade(db, version, mNewVersion);
                    }
                    db.setVersion(mNewVersion);
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
            }

            onOpen(db);
            success = true;
            return db;
        } finally {
            mIsInitializing = false;
            if (success) {
                if (mDatabase != null) {
                    try { mDatabase.close(); } catch (Exception e) { }
                    //mDatabase.unlock();
                }
                mDatabase = db;
            } else {
                //if (mDatabase != null) mDatabase.unlock();
                if (db != null) db.close();
            }
        }

    }

    /**
     * Create and/or open a database.  This will be the same object returned by
     * {@link #getWritableDatabase} unless some problem, such as a full disk,
     * requires the database to be opened read-only.  In that case, a read-only
     * database object will be returned.  If the problem is fixed, a future call
     * to {@link #getWritableDatabase} may succeed, in which case the read-only
     * database object will be closed and the read/write object will be returned
     * in the future.
     *
     * <p class="caution">Like {@link #getWritableDatabase}, this method may
     * take a long time to return, so you should not call it from the
     * application main thread, including from
     * {@link android.content.ContentProvider#onCreate ContentProvider.onCreate()}.
     *
     * @throws SQLiteException if the database cannot be opened
     * @return a database object valid until {@link #getWritableDatabase}
     *     or {@link #close} is called.
     */
    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            return mDatabase;  // The database is already open for business
        }

        if (mIsInitializing) {
            throw new IllegalStateException("getReadableDatabase called recursively");
        }

        try {
            return getWritableDatabase();
        } catch (SQLiteException e) {
            if (mName == null) throw e;  // Can't open a temp database read-only!
            Log.e(TAG, "Couldn't open " + mName + " for writing (will try read-only):", e);
        }

        SQLiteDatabase db = null;
        try {
            mIsInitializing = true;
            String path = mContext.getDatabasePath(mName).getPath();
            db = SQLiteDatabase.openDatabase(path, mFactory, SQLiteDatabase.OPEN_READONLY);
            if (db.getVersion() != mNewVersion) {
                throw new SQLiteException("Can't upgrade read-only database from version " +
                        db.getVersion() + " to " + mNewVersion + ": " + path);
            }

            onOpen(db);
            Log.w(TAG, "Opened " + mName + " in read-only mode");
            mDatabase = db;
            return mDatabase;
        } finally {
            mIsInitializing = false;
            if (db != null && db != mDatabase) db.close();
        }
    }

    /**
     * Close any open database object.
     */
    @Override
    public synchronized void close() {
        if (mIsInitializing) throw new IllegalStateException("Closed during initialization");

        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
            mDatabase = null;
        }
    }

    @Override
    public final void onConfigure(SQLiteDatabase db) {
        // not supported!
    }

    @Override
    public final void onCreate(SQLiteDatabase db) {
        // do nothing - createOrOpenDatabase() is called in
        // getWritableDatabase() to handle database creation.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TAG, "Upgrading database " + mName + " from version " + oldVersion + " to " + newVersion + "...");

        ArrayList<String> paths = new ArrayList<String>();
        getUpgradeFilePaths(oldVersion, newVersion-1, newVersion, paths);

        if (paths.isEmpty()) {
            Log.e(TAG, "no upgrade script path from " + oldVersion + " to " + newVersion);
            throw new SQLiteAssetException("no upgrade script path from " + oldVersion + " to " + newVersion);
        }

        Collections.sort(paths, new VersionComparator());
        for (String path : paths) {
            try {
                Log.w(TAG, "processing upgrade: " + path);
                InputStream is = mContext.getAssets().open(path);
                String sql = Utils.convertStreamToString(is);
                if (sql != null) {
                    List<String> cmds = Utils.splitSqlScript(sql, ';');
                    for (String cmd : cmds) {
                        //Log.d(TAG, "cmd=" + cmd);
                        if (cmd.trim().length() > 0) {
                            db.execSQL(cmd);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.w(TAG, "Successfully upgraded database " + mName + " from version " + oldVersion + " to " + newVersion);

    }

    @Override
    public final void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // not supported!
    }

    /**
     * Bypass the upgrade process (for each increment up to a given version) and simply
     * overwrite the existing database with the supplied asset file.
     *
     * @param version bypass upgrade up to this version number - should never be greater than the
     *                latest database version.
     *
     * @deprecated use {@link #setForcedUpgrade} instead.
     */
    @Deprecated
    public void setForcedUpgradeVersion(int version) {
        setForcedUpgrade(version);
    }

    /**
     * Bypass the upgrade process (for each increment up to a given version) and simply
     * overwrite the existing database with the supplied asset file.
     *
     * @param version bypass upgrade up to this version number - should never be greater than the
     *                latest database version.
     */
    public void setForcedUpgrade(int version) {
        mForcedUpgradeVersion = version;
    }

    /**
     * Bypass the upgrade process for every version increment and simply overwrite the existing
     * database with the supplied asset file.
     */
    public void setForcedUpgrade() {
        setForcedUpgrade(mNewVersion);
    }

    private SQLiteDatabase createOrOpenDatabase(boolean force) throws SQLiteAssetException {

        // test for the existence of the db file first and don't attempt open
        // to prevent the error trace in log on API 14+
        SQLiteDatabase db = null;
        File file = new File (mDatabasePath + "/" + mName);
        if (file.exists()) {
            db = returnDatabase();
        }
        //SQLiteDatabase db = returnDatabase();

        if (db != null) {
            // database already exists
            if (force) {
                Log.w(TAG, "forcing database upgrade!");
                copyDatabaseFromAssets();
                db = returnDatabase();
            }
            return db;
        } else {
            // database does not exist, copy it from assets and return it
            copyDatabaseFromAssets();
            db = returnDatabase();
            return db;
        }
    }

    private SQLiteDatabase returnDatabase(){
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(mDatabasePath + "/" + mName, mFactory, SQLiteDatabase.OPEN_READWRITE);
            Log.i(TAG, "successfully opened database " + mName);
            return db;
        } catch (SQLiteException e) {
            Log.w(TAG, "could not open database " + mName + " - " + e.getMessage());
            return null;
        }
    }

    private void copyDatabaseFromAssets() throws SQLiteAssetException {
        Log.w(TAG, "copying database from assets...");

        String path = mAssetPath;
        String dest = mDatabasePath + "/" + mName;
        InputStream is;
        boolean isZip = false;

        try {
            // try uncompressed
            is = mContext.getAssets().open(path);
        } catch (IOException e) {
            // try zip
            try {
                is = mContext.getAssets().open(path + ".zip");
                isZip = true;
            } catch (IOException e2) {
                // try gzip
                try {
                    is = mContext.getAssets().open(path + ".gz");
                } catch (IOException e3) {
                    SQLiteAssetException se = new SQLiteAssetException("Missing " + mAssetPath + " file (or .zip, .gz archive) in assets, or target folder not writable");
                    se.setStackTrace(e3.getStackTrace());
                    throw se;
                }
            }
        }

        try {
            File f = new File(mDatabasePath + "/");
            if (!f.exists()) { f.mkdir(); }
            if (isZip) {
                ZipInputStream zis = Utils.getFileFromZip(is);
                if (zis == null) {
                    throw new SQLiteAssetException("Archive is missing a SQLite database file");
                }
                Utils.writeExtractedFileToDisk(zis, new FileOutputStream(dest));
            } else {
                Utils.writeExtractedFileToDisk(is, new FileOutputStream(dest));
            }

            Log.w(TAG, "database copy complete");

        } catch (IOException e) {
            SQLiteAssetException se = new SQLiteAssetException("Unable to write " + dest + " to data directory");
            se.setStackTrace(e.getStackTrace());
            throw se;
        }
    }

    private InputStream getUpgradeSQLStream(int oldVersion, int newVersion) {
        String path = String.format(mUpgradePathFormat, oldVersion, newVersion);
        try {
            return mContext.getAssets().open(path);
        } catch (IOException e) {
            Log.w(TAG, "missing database upgrade script: " + path);
            return null;
        }
    }

    private void getUpgradeFilePaths(int baseVersion, int start, int end, ArrayList<String> paths) {

        int a;
        int b;

        InputStream is = getUpgradeSQLStream(start, end);
        if (is != null) {
            String path = String.format(mUpgradePathFormat, start, end);
            paths.add(path);
            //Log.d(TAG, "found script: " + path);
            a = start - 1;
            b = start;
            is = null;
        } else {
            a = start - 1;
            b = end;
        }

        if (a < baseVersion) {
            return;
        } else {
            getUpgradeFilePaths(baseVersion, a, b, paths); // recursive call
        }

    }

    /**
     * An exception that indicates there was an error with SQLite asset retrieval or parsing.
     */
    @SuppressWarnings("serial")
    public static class SQLiteAssetException extends SQLiteException {

        public SQLiteAssetException() {}

        public SQLiteAssetException(String error) {
            super(error);
        }
    }


    /**
     *
     * Usage (Tutorial)
     *
     * SQLiteAssetHelper is intended as a drop in alternative for the framework's SQLiteOpenHelper. Please familiarize yourself with the behaviour and lifecycle of that class.
     *
     * Extend SQLiteAssetHelper as you would normally do SQLiteOpenHelper, providing the constructor with a database name and version number:
     *
     * public class MyDatabase extends SQLiteAssetHelper {
     *
     *     private static final String DATABASE_NAME = "northwind.db";
     *     private static final int DATABASE_VERSION = 1;
     *
     *     public MyDatabase(Context context) {
     * 	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
     *     }
     * }
     * SQLiteAssetHelper relies upon asset file and folder naming conventions. Your assets folder will either be under your project root, or under src/main if you are using the default gradle project structure. At minimum, you must provide the following:
     *
     * A databases folder inside assets
     * A SQLite database inside the databases folder whose file name matches the database name you provide in code (including the file extension, if any)
     * For the example above, the project would contain the following:
     *
     * assets/databases/northwind.db
     * Earlier versions of this library required the database asset to be compressed within a ZIP archive. This is no longer a requirement, but is still supported. Applications still targeting Gingerbread (API 10) or lower should continue to provide a compressed archive to ensure large database files are not corrupted during the packaging process. The more Linux friendly GZIP format is also supported. The naming conventions using the above example are as follows:
     *
     * ZIP: assets/databases/northwind.db.zip (a single SQLite database file must be the only file within the archive)
     * GZIP: assets/databases/northwind.db.gz
     * The database will be extracted from the assets and copied into place within your application's private data directory. If you prefer to store the database file somewhere else (such as external storage) you can use the alternate constructor to specify a storage path. You must ensure that this path is available and writable whenever your application needs to access the database.
     *
     * super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
     * The database is made available for use the first time either getReadableDatabase() or getWritableDatabase() is called.
     *
     * The class will throw a SQLiteAssetHelperException if you do not provide the appropriately named file.
     *
     * The SQLiteOpenHelper methods onConfigure, onCreate and onDowngrade are not supported by this implementation and have been declared final.
     *
     * The samples:database-v1 project demonstrates a simple database creation and usage example using the classic Northwind database.
     *
     * Database Upgrades
     * At a certain point in your application's lifecycle you will need to alter it's database structure to support additional features. You must ensure users who have installed your app prior to this can safely upgrade their local databases without the loss of any locally held data.
     *
     * To facilitate a database upgrade, increment the version number that you pass to your SQLiteAssetHelper constructor:
     *
     * private static final int DATABASE_VERSION = 2;
     * Update the initial SQLite database in the project's assets/databases directory with the changes and create a text file containing all required SQL commands to upgrade the database from its previous version to it's current version and place it in the same folder. The required naming convention for this upgrade file is as follows:
     *
     * assets/databases/<database_name>_upgrade_<from_version>-<to_version>.sql
     * For example, northwind.db_upgrade_1-2.sql upgrades the database named "northwind.db" from version 1 to 2. You can include multiple upgrade files to upgrade between any two given versions.
     *
     * If there are no files to form an upgrade path from a previously installed version to the current one, the class will throw a SQLiteAssetHelperException.
     *
     * The samples:database-v2-upgrade project demonstrates a simple upgrade to the Northwind database which adds a FullName column to the Employee table.
     *
     * Generating upgrade scripts
     * You can use 3rd party tools to automatically generate the SQL required to modify a database from one schema version to another. One such application is SQLite Compare Utility for Windows.
     *
     * Upgrades via overwrite
     * If you have a read-only database or do not care about user data loss, you can force users onto the latest version of the SQLite database each time the version number is incremented (overwriting the local database with the one in the assets) by calling the setForcedUpgrade() method in your SQLiteAsstHelper subclass constructor.
     *
     * You can additionally pass an argument that is the version number below which the upgrade will be forced.
     *
     * Note that this will overwrite an existing local database and all data within it.*/

}
