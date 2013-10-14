package az.neuron.ask.util;


import az.neuron.ask.domain.Operation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class Helper {

    public static long isExist(Map<String, List<Operation>> map ,String modulLinkId , Long operationId){
        long result = 0;
        List<Operation> operationList = map.get(modulLinkId);
        for (Operation operation : operationList){
            if(operation.getId()==operationId){
                result = operation.getModulOperationId();
                break;
            }
        }
       return result;
    }

    public static boolean checkedCheck(List<Operation> operationList, long modulOperationId){
        boolean checked = false;
        for(int i=0; i<operationList.size(); i++){
            if(operationList.get(i).getModulOperationId()==modulOperationId){
                 checked = true;
                break;
            }
        }
        return checked;
    }

    public static String toMD5(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = new byte[64];
        md.update(string.getBytes(), 0, string.length());
        md5 = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : md5) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }

    public static String readFile(String filename) {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        try {
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    public static String separateText(String text, int length){
        String retText = text;
        if (text.length() > length){
            retText = text.replaceFirst(" " , "</br>");
        }
        return retText;
    }
}
