package com.dangelo.xxoa.bean;

/**
 * Created by dangelo on 16/12/22.
 */
public class download {
    /**
     * "Download":{"docId":"4830","uuEName":"xtgly","docPath":"","file":"http://10.54.40.50/download/app/download.do?docId=4830"}
     */
    private String docId;
    private String uuEName;
    private String docPath;
    private String file;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getUuEName() {
        return uuEName;
    }

    public void setUuEName(String uuEName) {
        this.uuEName = uuEName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
