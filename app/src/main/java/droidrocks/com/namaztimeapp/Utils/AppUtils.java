package droidrocks.com.namaztimeapp.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;


import com.google.android.material.snackbar.Snackbar;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import droidrocks.com.namaztimeapp.R;


public class AppUtils {

    public static final String TAG = "APP_UTIL_TAG";


    public static Intent getOpenFacebookIntent(Context context) {
        // this will redirect user to facebook
        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://groups/235096546107840")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/groups/235096546107840")); //catches and opens a url to the desired page
        }
    }


    public static String roundDoubleWithTwoDecimals(double value) {
        double parse_value = parseStringToDouble(String.valueOf(value));
        DecimalFormat decimalFormat = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return decimalFormat.format(parse_value);
    }

    public static double parseStringToDouble(String value) {
        try {
            // Try to directly parse the input string assuming it's in a standard format.
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // If direct parsing fails, attempt to convert Bengali numerals to Arabic numerals.
            String convertedValue = convertBengaliNumeralsToArabic(value);
            try {
                // Try to parse again after conversion.
                return Double.parseDouble(convertedValue);
            } catch (NumberFormatException e2) {
                // If parsing still fails, handle the error appropriately.
                // This could involve logging the error, throwing a custom exception,
                // or using a default value.
                throw new NumberFormatException("Cannot parse the string to a double: " + value);
            }
        }
    }

    private static String convertBengaliNumeralsToArabic(String value) {
        char[] bengaliNumerals = {'০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'};
        StringBuilder arabicValue = new StringBuilder();

        for (char character : value.toCharArray()) {
            boolean found = false;
            for (int i = 0; i < bengaliNumerals.length; i++) {
                if (character == bengaliNumerals[i]) {
                    arabicValue.append(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // If the character is not a Bengali numeral, append it directly.
                arabicValue.append(character);
            }
        }

        return arabicValue.toString();
    }


    public static void sendTextMessage(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + number));  // This ensures only SMS apps respond
        // intent.putExtra("sms_body", fetchTextMessageString());
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            AppUtils.showNegetiveSnackBar((Activity) context, "No SMS app installed on your device");
        }
    }

    public static void shareText(Context context, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public static boolean ValidateDate(final String input) {
        // Compile regular expression
        String regx = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        final Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        // Match regex against input
        final Matcher matcher = pattern.matcher(input);
        // Use results...
        return matcher.matches();
    }

    /**
     * count all clicks
     */
    public static void countClick(Context context) {
        SpUtil spUtil = SpUtil.INSTANCE;
        int current_value = spUtil.getInteger(context, Constance.CLICK_COUNT_KEY, 0);
        spUtil.storeInteger(context, Constance.CLICK_COUNT_KEY, current_value + 1);
        //   Toast.makeText(context, "Click cunt  =" + spUtil.getInteger(context, Constance.CLICK_COUNT_KEY, 0), Toast.LENGTH_SHORT).show();
    }

    /**
     * reset all clicks to zero
     */
    public static void resetCounterClick(Context context) {
        SpUtil spUtil = SpUtil.INSTANCE;
        spUtil.storeInteger(context, Constance.CLICK_COUNT_KEY, 0);
    }

    /**
     * return a int value of total click
     */
    public static int getTotalClick(Context context) {
        SpUtil spUtil = SpUtil.INSTANCE;
        return spUtil.getInteger(context, Constance.CLICK_COUNT_KEY, 0);
    }


    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * @param bitmap
     * @return String (from given bitmap)
     */
    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d(TAG, "BitmapToString: " + temp);
        return temp;

    }

    /**
     * this will check internet connection
     */
    public static boolean checkInternet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static void closeKeyboard(Activity context) {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = context.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {
            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    context.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    /**
     * reduces the size of the image
     *
     * @param image
     * @param maxSize
     * @return
     */
    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    /**
     * this method will create uri to bitmap
     */
    public static Bitmap uriToBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
            } else {
                ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, uri);
                bitmap = ImageDecoder.decodeBitmap(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * this method will check empty bitmap
     */
    public static boolean checkEmptyBitmap(Bitmap bitmap) {
        Bitmap emptyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        // myBitmap is empty/blank
        return bitmap.sameAs(emptyBitmap);
    }

    /**
     * this will send any url to browser
     */
    public static void SendUrlToBrowser(String url, Context context) {
        if (url == null) {
            // handle null case
            return;
        }
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }


    /**
     * this will open url in new custom tab
     */
    public static void OpenCustomTab(Context context, String url) {
        int colorInt = context.getColor(R.color.purple_500); //red
        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(colorInt)
                .build();

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setDefaultColorSchemeParams(defaultColors);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));

    }

    /**
     * @param context
     * @param message static method which will return a "GREEN" color Snake bar
     */
    public static void showPositiveSnackBar(Activity context, String message) {

        View parentLayout = context.findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                /*.setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })*/
                .setTextColor(context.getColor(R.color.white))
                .setBackgroundTint(context.getColor(R.color.green))
                .setActionTextColor(context.getColor(R.color.teal_200))
                .show();


    }


    /**
     * @param context
     * @param message static method which will return a red color Snake bar
     */
    public static void showNegetiveSnackBar(Activity context, String message) {

        View parentLayout = context.findViewById(android.R.id.content);

        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                /*.setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })*/
                .setTextColor(context.getColor(R.color.white))
                .setBackgroundTint(context.getColor(R.color.red))
                .setActionTextColor(context.getColor(R.color.teal_200))
                .show();


    }

    /**
     * @param context
     * @param message static method which will return a red color Snake bar
     */
    public static void showRegularAlertSnackBar(Activity context, String message) {

        View parentLayout = context.findViewById(android.R.id.content);

        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                /*.setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })*/
                .setTextColor(context.getColor(R.color.purple_700))
                .setBackgroundTint(context.getColor(R.color.purple_200))
                .setActionTextColor(context.getColor(R.color.teal_200))
                .show();


    }

    /**
     * this will return current date as a string
     */
    public static String getCurrentDate() {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return dateFormat.format(date);
    }


    /**
     * this will return current date as a string
     */
    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        return df.format(c.getTime());
    }


    /**
     * this will return current month as a string
     */
    public static int getCurrentMonth() {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return Integer.parseInt(dateFormat.format(date));
    }

    /**
     * this will return current day as a string
     */
    public static int getCurrentDay() {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return Integer.parseInt(dateFormat.format(date));
    }

    /**
     * this will return current year as a string
     */
    public static int getCurrentYear() {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return Integer.parseInt(dateFormat.format(date));
    }


    /**
     * this method is responsible for creating a layout view into Bitmap image
     *
     * @param view pass any layout view to this params
     */

    public static Bitmap createBitmapFromLayout(View view) {
        // view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = null;
        returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        /*if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        }else {
            returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.RGB_565);
        }*/

        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.TRANSPARENT);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    /**
     * this method is responsible for
     * save image to memory for all kinds of android version app
     * ============================================
     *
     * @param context     is activity context
     * @param bitmap      is the bitmap Image which you want to save
     * @param imageHeight the height of the image
     * @param imageWidth  the width of the image
     * @param imageName   the name of the image
     * @param callback    this callback will return a boolean value
     *                    =============================================
     */
    public static void saveImageForAllAndroid(Context context, Bitmap bitmap,
                                              @NonNull String imageName,
                                              int imageHeight,
                                              int imageWidth, AppUtilsCallback callback) {
        OutputStream fos;
        String imagesDir = null;
        String timeStamp = String.valueOf(Calendar.getInstance().getTimeInMillis());

        try {
            // this is for Android10
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // this permission required  <uses-permission android:name="android.permission.ACCESS_MEDIA_LOCATION"/>
                ContentResolver resolver = context.getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, imageName + timeStamp);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" + context.getResources().getString(R.string.app_name));
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                fos = resolver.openOutputStream(imageUri);
            } else {
                ///sdcard/DCIM/NewEmoteMaker
                imagesDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM).toString() + File.separator + context.getResources().getString(R.string.app_name);
                File file = new File(imagesDir);
                if (!file.exists()) {
                    file.mkdir();
                }
                File image = new File(imagesDir, imageName + timeStamp + ".png");
                fos = new FileOutputStream(image);
            }


            Bitmap imageBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true);
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            callback.onCallback(true);
        } catch (Exception e) {
            callback.onCallback(false);
            e.printStackTrace();

        }


        if (imagesDir != null) {
            //  pathname = new File(imagesDir).getAbsolutePath();
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(new File(imagesDir)); //out is your file you saved/deleted/moved/copied
            mediaScanIntent.setData(contentUri);
            context.sendBroadcast(mediaScanIntent);

        }

    }

    public static void copyTextToClipboard(Context context, String text, AppUtilsCallback callback) {
        try {
            ClipboardManager clipboard = (ClipboardManager)
                    context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", text);
            clipboard.setPrimaryClip(clip);
            callback.onCallback(true);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onCallback(false);
        }
    }


    public static String loadJSONFromAsset(Context context, String jsonFileNameWithExtinction) {
        // this method will return json data from local json file
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileNameWithExtinction);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public interface AppUtilsCallback {
        void onCallback(boolean b);
    }


}
