package az.neuron.ask.util;

import az.neuron.ask.domain.UploadForm;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

//import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: irkan
 * Date: 4/7/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadUtility {

    //private static final Logger logger = Logger.getLogger(FileUploadUtility.class);

    public static UploadForm getUploadedFile(HttpServletRequest request) throws Exception {
        UploadForm uploadForm = new UploadForm();

//        File file = null;
//        HttpSession session = request.getSession();
//        String path = request.getServletContext().getRealPath(Constants.UPLOAD_DIR + session.getId());
//        String path = request.getServletContext().getRealPath("C:\\Users\\admin\\Documents\\e-notary\\scanFolder");

//        File uploadFolder = new File(path);

//        uploadFolder.mkdirs();
//        uploadFolder.mkdir();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        List<FileItem> items = new ServletFileUpload(factory).parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString("UTF-8");
                System.out.println(fieldName + " = " + fieldValue);
                uploadForm.put(fieldName, fieldValue);
            }
//            else {
//                OutputStream os = null;
//                try {
//                    file = new File(uploadFolder, item.getFieldName());
//                    String filename = FilenameUtils.getName(item.getName());
//                    String extension = "";
//                    int position = filename.lastIndexOf(".");
//                    if(position > 0) {
//                        extension = filename.substring(position);
//                    }
//                    logger.debug("Received file " + FilenameUtils.getName(item.getName()));
//                    file = new File(uploadFolder, "" + IdGenerator.generateId() + extension);
//                    os = new FileOutputStream(file);
//                    copyFromInputStreamToOutputStream(item.getInputStream(), os);
//                    uploadForm.setFile(file);
//                } catch (Throwable e) {
//                    logger.error(e);
//                    e.printStackTrace();
//                    throw new Exception(e.getMessage());
//                } finally {
//                    if (os != null) {
//                        try {
//                            os.flush();
//                            os.close();
//                        } catch (IOException e) {
//                            logger.error(e);
//                        }
//
//                    }
//                }
//            }
        }

        return uploadForm;
    }

    public static void copyFromInputStreamToOutputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1000000];
        while (true) {
            synchronized (buffer) {
                int amountRead = in.read(buffer);
                if (amountRead == -1)
                    break;
                out.write(buffer, 0, amountRead);
            }
        }
        in.close();
        out.flush();
        out.close();
    }

    public static void copyFromOutputStreamToInputStream(OutputStream out, InputStream in) throws Exception{

    }

    public static void openScanPanel() throws Exception{
        Runtime run = Runtime.getRuntime();
        Process pp = run.exec("C:\\Program Files (x86)\\HP\\Digital Imaging\\bin\\Hpqdirec.exe");
        BufferedReader in = new BufferedReader(new InputStreamReader(pp.getInputStream()));
        String line;

        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
    public static File getUploadedFileForScan() throws Exception {
        boolean success;
        File returnFile = null;
        try {

//          File file = null;
            File file2;
            String path = "D:\\ULTRA COMPANY\\E-NOTARY\\trunk\\uploadedFile\\" +
                    "";

            File uploadFolder = new File(path);

            uploadFolder.mkdirs();
            uploadFolder.mkdir();
            InputStream ins = null;
            OutputStream os = null;
//            returnFile = new File(uploadFolder, "" + IdGenerator.generateId() + ".pdf");
            file2 = new File("C:\\Users\\admin\\Documents\\e-notary\\scanFolder", "scan0001.pdf");
            os = new FileOutputStream(returnFile);

            ins = new FileInputStream(file2);
            copyFromInputStreamToOutputStream(ins, os);
            file2.delete();
            success = true;

        } catch (Exception ex) {
            ex.printStackTrace();
            success = false;
        }
        return returnFile;
    }
}
