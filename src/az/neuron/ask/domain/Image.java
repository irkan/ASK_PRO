package az.neuron.ask.domain;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 9/28/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Image extends Base {
    private File file;
    private String filePath;
    private String ftpFilePath;
    private String fileName;
    private String realPath;
    private String fileExtension;
    private String personType;

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFtpFilePath() {
        return ftpFilePath;
    }

    public void setFtpFilePath(String ftpFilePath) {
        this.ftpFilePath = ftpFilePath;
    }
}
