package json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import OfficialDescripton.OfficialAPIs;
import Pattern.PersonalPattern;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonConvert {  
	public static void main(String[] args) {
		String JsonObj = JsonConvert.ReadFile("/Users/Liam_Xu/Desktop/top_n_feature_sample.json");
//		System.out.print(JsonObj);
		JSONObject JB = JSONObject.fromObject(JsonObj);
//		store all keys in Json in this FIle
		Iterator<String> KeyIt = JB.keys(); 
//		Iterate all keys in the set
		while (KeyIt.hasNext()) {
			String TempKey = (String) KeyIt.next();
//			System.out.println(TempKey);
			JsonConvert.AnalyzeKey(TempKey, JB);
		}
		
//		System.out.println(JB);
//		JsonConvert.AnalyzeKey("agagswe.technological.news.data", JB);
//		JsonConvert.AnalyzeKey("air.air.FootballTacticsAndroid.data", JB);
//		JsonConvert.AnalyzeKey("gen10_07fcf9ff-3625-46e8-b470-29ee05877e0a_app-release.data", JB);
//		JsonConvert.AnalyzeKey("gen10_06515735-79ae-48e2-a52b-5a8e2a5befee_app-release.data", JB);
//		JsonConvert.AnalyzeKey("air.air.com.jessoft.flvplayer.FLVPlayer.data", JB);
//		JsonConvert.AnalyzeKey("gen10_187d3dca-294b-40f3-9eb3-f3b7639e655e_app-release.data", JB);
	}
	
	
	/**
	 * Analyze the front key through name
	 * @param KeyName
	 * @param JB
	 */
	public static void AnalyzeKey(String KeyName, JSONObject JB){
//		Get the front key of the file
		JSONObject temp = (JSONObject) JB.get(KeyName);
//		System.out.println(temp.get("top_features"));
//		put all data in top_features into an array
		JSONArray JA = (JSONArray) temp.get("top_features");
		JSONArray JA1 = (JSONArray) JA.get(0);
		ArrayList<String> SensitiveAPIS = new ArrayList<String>();
//		System.out.println(JA1.get(1));
//		print all data
		for (int i = 0; i < JA.size(); i++) {
			String TempAPI = (String) ((JSONArray) JA.get(i)).get(1);
//			Judge if TempAPI name have the sensitive string
			if (JsonConvert.IsHaveString(TempAPI, "android.permission")) {
				SensitiveAPIS.add(TempAPI);
				
			}
		}
		JsonConvert.GenerateDescription(SensitiveAPIS);
//		Customize the pattern by users. 
		String[] APIPattern = new String[]{"internet", "write_external_storage"};
		PersonalPattern.PatternDescription(SensitiveAPIS,APIPattern);
		System.out.println("==========================");
	}
	
	/**
	 * Matching the description with the last name(Key)
	 * @param SensitiveAPIS
	 */
	public static void GenerateDescription(ArrayList<String> SensitiveAPIS){
		Map TempAPIs = OfficialAPIs.DescriptionSet();
		for (int i = 0; i < SensitiveAPIS.size(); i++) {
//			System.out.println(SensitiveAPIS.get(i));
			int LastPoint = SensitiveAPIS.get(i).lastIndexOf('.');
			String LastString = SensitiveAPIS.get(i).substring(LastPoint+1);
//			System.out.println(LastString);
//			TempAPIs.get(LastString);
			System.out.println(SensitiveAPIS.get(i));
			System.out.println(TempAPIs.get(LastString.toUpperCase()));
			System.out.println("------------------------------");
		}
		
	}
	
	
	/**
	 * Matching the Senitive API
	 * @param MatchingStr
	 * @param Rex
	 * @return
	 */
	public static boolean IsHaveString(String MatchingStr, String Rex){
		Rex = "(.*)"+Rex+"(.*)";
		if (MatchingStr.matches(Rex)){
			return true;
		}
		return false;
	}
	
	/**
	 * Read the file from the path
	 * @param path
	 * @return
	 */
	public static String ReadFile(String path){
		String laststr="";
		File file=new File(path);
		BufferedReader reader=null;
		try{
		reader=new BufferedReader(new FileReader(file));
		String tempString=null;
		//int line=1;
		while((tempString=reader.readLine())!=null){
		//System.out.println("line"+line+":"+tempString);
		laststr=laststr+tempString;
		//line++;
		}
		reader.close();
		}catch(IOException e){
		e.printStackTrace();
		}finally{
		if(reader!=null){
		try{
		reader.close();
		}catch(IOException el){
		}  }  }
		return laststr;
		}
}  
