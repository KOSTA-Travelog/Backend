package kosta.travelog.servlet;

import java.io.Serializable;
import java.util.Objects;

public class URLModel implements Serializable {
    private static final long serialVersionUID = 3103397263901988333L;
    private String page;
    private boolean flag;

    public URLModel() {
        this("response.jsp", false);
    }

    public URLModel(String page) {
        this(page, false);
    }

    public URLModel(String page, boolean flag) {
        setPage(page);
        setFlag(flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        URLModel urlModel = (URLModel) o;
        return isFlag() == urlModel.isFlag() && Objects.equals(getPage(), urlModel.getPage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getPage());
        result = 31 * result + Boolean.hashCode(isFlag());
        return result;
    }

    @Override
    public String toString() {
        String sb = "URLModel{" + "flag=" + flag +
                ", page='" + page + '\'' +
                '}';
        return sb;
    }
}