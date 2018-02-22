package Pattern;

import java.util.ArrayList;
import java.util.Map;

import OfficialDescripton.OfficialAPIs;
import json.JsonConvert;

public class PersonalPattern {
	/**
	 * Pattern the description
	 * @param SensitiveAPIS
	 * @param APIPattern
	 */
	public static void PatternDescription(ArrayList<String> SensitiveAPIS, String[]APIPattern){
		ArrayList<String> PatternAPIS = new ArrayList<String>(); // store pattern API
		Boolean IshaveP1 = false;
		Boolean IshaveP2 = false;
		for (int i = 0; i < SensitiveAPIS.size(); i++) {
			String TemmpAPI = SensitiveAPIS.get(i);
			if (TemmpAPI.contains(APIPattern[0])){
				IshaveP1 = true;
				PatternAPIS.add(TemmpAPI);
			}
			if (TemmpAPI.contains(APIPattern[1])){
				IshaveP2 = true;
				PatternAPIS.add(TemmpAPI);
			}
		}
//		out put pattern description
		if (IshaveP1 && IshaveP2){
			System.out.println("PATTERN DESCRPTION");
			System.out.println(PatternAPIS);
			PersonalPattern.GenerateDescription(PatternAPIS);
		}
	}



	/**
	 * Matching the description with the last name(Key)
	 * @param SensitiveAPIS
	 */
	public static void GenerateDescription(ArrayList<String> SensitiveAPIS){
		Map TempAPIs = OfficialAPIs.DescriptionSet();
		ArrayList<String> PatternDescription = new ArrayList<String>(); // store pattern description
		String FinalDescrption = ""; 
		for (int i = 0; i < SensitiveAPIS.size(); i++) {
//			System.out.println(SensitiveAPIS.get(i));
			int LastPoint = SensitiveAPIS.get(i).lastIndexOf('.');
			String LastString = SensitiveAPIS.get(i).substring(LastPoint+1);
//			System.out.println(LastString);
//			TempAPIs.get(LastString);
			PatternDescription.add((String) TempAPIs.get(LastString.toUpperCase()));
//			System.out.println(TempAPIs.get(LastString.toUpperCase()));
//			
		}
		int indexofTo = PatternDescription.get(1).indexOf("to");
		FinalDescrption = PatternDescription.get(0) + "and " + PatternDescription.get(1).substring(indexofTo);
		System.out.println(FinalDescrption);

		System.out.println("------------------------------");
		
	}
	
}
