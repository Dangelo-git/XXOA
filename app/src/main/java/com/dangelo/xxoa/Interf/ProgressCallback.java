package com.dangelo.xxoa.Interf;

import com.dangelo.xxoa.bean.FileInfo;

/**
 * Created by dangelo on 16/12/21.
 */
public interface ProgressCallback {
    public void DialogProgress(int count);
    public void ProgressFinish(FileInfo info);
    public void Progressfailure(FileInfo info);
}
