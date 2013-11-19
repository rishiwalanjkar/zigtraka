package subscription.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.provider.Settings.Secure;
import android.widget.Toast;

public class KeyGenerator {
	private static String android_id;
	private static String subcriptionKey;

	public static String getKey(Context context, String ShopName,
			String subcriptionPeriod) {
		android_id = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
		String timeStamp = s.format(new Date());
		try {
			subcriptionKey = AES.bin2hex(AES.encrypt(ShopName +  timeStamp + subcriptionPeriod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xOR(subcriptionKey);
	}

	private static String xOR(String subcriptionKey) {
		StringBuilder result = new StringBuilder();
		String alphaNumeric="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int limit = subcriptionKey.length() / 2;
        Random r=new Random();
        
		for (int i = 0; i < limit; i++) {
			char c = (char) (subcriptionKey.charAt(i) ^ subcriptionKey
					.charAt(limit+i));

			if (c <48 || (c>57&&c<65)|| (c>90&&c<97)||c >122)
				c = alphaNumeric.charAt(r.nextInt(62));

			result.append(c);
		}

		if (subcriptionKey.length() % 2 != 0) {
			result.append(subcriptionKey.charAt(limit + limit));
		}
		return result.toString();

	}
}
