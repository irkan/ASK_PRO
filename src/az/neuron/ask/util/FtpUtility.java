package az.neuron.ask.util;

import az.neuron.ask.domain.Image;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: irkan
 * Date: 8/30/12
 * Time: 10:56 PM
 * To change this template use File | Settings | File Templates.*/



public class FtpUtility {


    public static String ftpTransferInsertImage(Image image) throws Exception{
//        String server = "82.194.5.11";
        String server = "192.168.190.11";
        String username = "tomcat";
        String password = "1q2w3e4r5t";
//        String password = "AD!E#R!1r1R!ED";
        String separator = "/";
        if(File.separator.equals("\\")){
            separator = "/";
        } else {
            separator = File.separator;
        }
//        String direction = separator + "ftp" + separator + "OIM" + separator + "images" + separator;
        String direction = separator + "ftp" + separator + "ELIBRARY" + separator + "images" + separator;
        InputStream in = null;
        FTPClient ftp = new FTPClient();
        String imageStr = "";
        try{
            ftp.connect(server, 21);
            ftp.login(username, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(direction);

            ftp.makeDirectory("ORG_LOGO");
            direction += "ORG_LOGO";
            ftp.changeWorkingDirectory(direction);
            File existsFile = new File(image.getRealPath(),image.getFileName()+".jpg");
            if(existsFile.exists()){
                existsFile.delete();
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftp.disconnect();
                    return "no path";
                }
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                File file = image.getFile();
                in = new FileInputStream(file);
                imageStr = image.getFileName() + ".jpg";
                ftp.storeFile(imageStr, in);
            } else {
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftp.disconnect();
                    return "no path";
                }
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                File file = image.getFile();
                in = new FileInputStream(file);
                imageStr = image.getFileName() + ".jpg";
                ftp.storeFile(imageStr, in);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "no path";
        }
        finally {
            in.close();
            ftp.logout();
            ftp.disconnect();
        }
        return direction + "/" + imageStr;
    }

    public static String downloadImageFile (Image image) throws Exception{
        String returnImageName = "";
//        String server = "82.194.5.11";
        String server = "192.168.190.11";
        String username = "tomcat";
        String password = "1q2w3e4r5t";
//        String password = "AD!E#R!1r1R!ED";
        String separator = "/";
        if(File.separator.equals("\\")){
           separator = "/";
        } else {
            separator = File.separator;
        }
        String fileExtension = "jpg";
        if(image.getFileExtension()!=null && !image.getFileExtension().equals("") ){
            fileExtension = image.getFileExtension();
        }
        String remotePath  = separator + "ftp" + separator + "ELIBRARY" + separator + "images" + separator + "ORG_LOGO" + separator + image.getFileName() + "." + fileExtension;
//        String remotePath  = separator + "ftp" + separator + "E-LIB" + separator + "images" + separator + "ORG_LOGO" + separator + image.getFileName() + ".jpg";
        String imageName = remotePath.split(separator)[(remotePath.split(separator).length)-1];
        remotePath = remotePath.split(imageName)[0];
        FTPClient client = new FTPClient( );
        OutputStream outStream = null;
        try {
            client.connect(server);
            client.login(username, password);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            File uploadFolder = new File(image.getRealPath());
            uploadFolder.mkdirs();
            uploadFolder.mkdir();

            File file = new File(uploadFolder,imageName);
            client.changeWorkingDirectory(remotePath);
            String[] names = client.listNames();
            for(int i=0; i<names.length; i++){
                System.out.println("names[i]===" + names[i]);
                System.out.println("image.getFileName()===" + image.getFileName() + ".jpg");
               if(names[i].equals(image.getFileName() + "." + fileExtension)){
                   outStream = new FileOutputStream( file );
                   client.changeWorkingDirectory(remotePath);
                   FileUploadUtility.copyFromInputStreamToOutputStream(client.retrieveFileStream(imageName), outStream);
                   image.setFilePath("images" + File.separator + imageName);
                   returnImageName = imageName;
                   break;
               } else {
                   returnImageName = "no_image";
               }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println( "Error communicating with FTP server." );
            returnImageName = "no_image";
        } finally {
            if(outStream!=null){
                outStream.close();
            }

            client.logout();
            client.disconnect();
        }
        return returnImageName;
    }

    public static String ftpTransferInsertPersonImage(Image image) throws Exception{
//        String server = "82.194.5.11";
        String server = "192.168.190.11";
        String username = "tomcat";
        String password = "1q2w3e4r5t";
//        String password = "AD!E#R!1r1R!ED";
        String separator = "/";
        if(File.separator.equals("\\")){
            separator = "/";
        } else {
            separator = File.separator;
        }
//        String direction = separator + "ftp" + separator + "OIM" + separator + "images" + separator;
        String direction = separator + "ftp" + separator + "ELIBRARY" + separator + "images" + separator;
        InputStream in = null;
        FTPClient ftp = new FTPClient();
        String imageStr = "";
        try{
            ftp.connect(server, 21);
            ftp.login(username, password);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(direction);

            ftp.makeDirectory(image.getPersonType());
            direction += image.getPersonType() + separator;
            ftp.changeWorkingDirectory(direction);

            File existsFile = new File(image.getRealPath(),image.getFileName()+".png");
            if(existsFile.exists()){
                existsFile.delete();
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftp.disconnect();
                    return "no path";
                }
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                File file = image.getFile();
                in = new FileInputStream(file);
                imageStr = image.getFileName() + ".png";
                ftp.storeFile(imageStr, in);
            } else {
                int reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftp.disconnect();
                    return "no path";
                }
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                File file = image.getFile();
                in = new FileInputStream(file);
                imageStr = image.getFileName() + ".jpg";
                ftp.storeFile(imageStr, in);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "no path";
        }
        finally {
            in.close();
            ftp.logout();
            ftp.disconnect();
        }
        return direction + "/" + imageStr;
    }

    public static String downloadPersonImageFile (Image image) throws Exception{
        String returnImageName = "";
//        String server = "82.194.5.11";
        String server = "192.168.190.11";
        String username = "tomcat";
        String password = "1q2w3e4r5t";
//        String password = "AD!E#R!1r1R!ED";
        String separator = "/";
        if(File.separator.equals("\\")){
            separator = "/";
        } else {
            separator = File.separator;
        }
//        String remotePath  = separator + "ftp" + separator + "OIM" + separator + "images" + separator + "READER" + separator + image.getName() + ".jpg";
        String remotePath  = separator + "ftp" + separator + "ELIBRARY" + separator + "images" + separator + image.getPersonType() + separator + image.getFileName() + ".jpg";
        String imageName = remotePath.split(separator)[(remotePath.split(separator).length)-1];
        remotePath = remotePath.split(imageName)[0];
        FTPClient client = new FTPClient();
        OutputStream outStream = null;
        try {
            client.connect(server);
            client.login(username, password);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            File uploadFolder = new File(image.getRealPath());
            uploadFolder.mkdirs();
            uploadFolder.mkdir();

            File file = new File(uploadFolder,imageName);
            client.changeWorkingDirectory(remotePath);
            String[] names = client.listNames();
            for(int i=0; i<names.length; i++){
                if(names[i].equals(image.getFileName() + ".jpg") || names[i].equals(image.getFileName() + ".png")){
                    outStream = new FileOutputStream( file );
                    client.changeWorkingDirectory(remotePath);
                    FileUploadUtility.copyFromInputStreamToOutputStream(client.retrieveFileStream(imageName), outStream);
                    image.setFilePath("images" + File.separator + imageName);
                    returnImageName = imageName;
                    break;
                } else {
                    returnImageName = "no_image";
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println( "Error communicating with FTP server." );
            returnImageName = "no_image";
        } finally {
//            outStream.close();
            client.logout();
            client.disconnect();
        }
        return returnImageName;
    }

    public static String downloadUserImage(Image image) throws Exception{
        String returnImageName = "";
//        String server = "82.194.5.11";
        String server = "192.168.190.11";
        String username = "tomcat";
        String password = "1q2w3e4r5t";
//        String password = "AD!E#R!1r1R!ED";
        String separator = "/";
        if(File.separator.equals("\\")){
            separator = "/";
        } else {
            separator = File.separator;
        }
//        String remotePath  = separator + "ftp" + separator + "OIM" + separator + "images" + separator + "READER" + separator + image.getName() + ".jpg";
        String remotePath  = separator + "ftp" + separator + "ELIBRARY" + separator + "images" + separator + image.getPersonType() + separator + image.getFileName() + ".jpg";
        String imageName = remotePath.split(separator)[(remotePath.split(separator).length)-1];
        remotePath = remotePath.split(imageName)[0];
        FTPClient client = new FTPClient();
        OutputStream outStream = null;
        try {
            client.connect(server);
            client.login(username, password);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            File uploadFolder = new File(image.getRealPath());
            uploadFolder.mkdirs();
            uploadFolder.mkdir();

            File file = new File(uploadFolder,imageName);
            client.changeWorkingDirectory(remotePath);
            String[] names = client.listNames();
            for(int i=0; i<names.length; i++){
                System.out.println("names[i]===" + names[i]);
                System.out.println("image.getFileName()===" + image.getFileName() + ".png");
                if(names[i].equals(image.getFileName() + ".jpg") || names[i].equals(image.getFileName() + ".png")){
                    outStream = new FileOutputStream( file );
                    client.changeWorkingDirectory(remotePath);
                    FileUploadUtility.copyFromInputStreamToOutputStream(client.retrieveFileStream(imageName), outStream);
                    image.setFilePath("images" + File.separator + imageName);
                    returnImageName = "upload/EMPLOYEE/" + imageName;
                    break;
                } else {
                    returnImageName = "images/profile-picture.jpg";
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println( "Error communicating with FTP server." );
            returnImageName = "images/profile-picture.jpg";
        } finally {
//            outStream.close();
            client.logout();
            client.disconnect();
        }
        return returnImageName;
    }
}
