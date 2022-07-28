/*
Copyright 2022 Rairye
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/


package korean_sentence_tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Tokenizer {

	private static HashSet<String> breaks = new HashSet<>(Arrays.asList("。", "！", "？", "．", ".", "?", "!"));
	private static HashSet<String> closingPunctuation = new HashSet<>(Arrays.asList(")", "]", "}", "）", "」", "】", "』", "｝", "〕", ">", "＞", "》", "〉", "］", "﹂", "\"", ")", "]", ">", "}"));
	private static HashSet<String> numbers = new HashSet<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "０", "１", "２", "３", "４", "５", "６", "７", "８", "９"));
	
	private static boolean isBreak(String c) {
	    return breaks.contains(c);
			
		}

	private static boolean isClosingPunct(String c) {
	    return closingPunctuation.contains(c);
		
		}
		
	private static boolean isNumeric(String c){
		return numbers.contains(c);
		
		}
	
	public ArrayList<String> tokenizeKoreanParagraph(String paragraph){

		ArrayList<String> sentences = new ArrayList<>();
		int paragraphLength = paragraph.length();
		
        if (paragraph instanceof String == false) {
            return sentences;
		
		}

        if (paragraphLength == 0) {
        	 return sentences;
	
		}
    
        String lastCategory = "";

        int i = 0;
        int j = 0;

        while (j < paragraphLength) {
                String currentChar = paragraph.substring(j, j+1);
                String currentCategory = isBreak(currentChar) == true ? "BREAK" : "NOTBREAK";
				
                if (lastCategory == "BREAK" && currentCategory == "NOTBREAK") {
                    
                        if (!(isClosingPunct(currentChar) && !(isNumeric(currentChar)))) {
                                sentences.add(paragraph.substring(i, j).trim());
                                lastCategory = "";
				currentCategory = "";
                                i = j;
                                j++;
								
						}
                                                             
                        else {
                                lastCategory = "BREAK";
                                j++;
				
						}		
								
				}
                                
                else if (currentCategory == "BREAK" && j < paragraphLength -1) {
                        j++;
                        lastCategory = currentCategory;
						
				}

                else if (j == paragraphLength - 1) {
                     sentences.add(paragraph.substring(i).trim());
                     j++;
                     
				}
				
                else {
                     j++;
                     lastCategory = currentCategory;
					 
				}
        
		}
		
        return sentences;
		
	}
	
		
}
