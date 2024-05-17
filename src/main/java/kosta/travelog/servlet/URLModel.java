package kosta.travelog.servlet;


import java.util.HashMap;
import java.util.Map;

public class URLModel {
    private String page;
    private Map<String, String> data;
    private boolean flag;

    public URLModel(Map<String, String> data) {
        this("index.jsp", data, false);
    }

    public URLModel(String page) {
        this(page, false);
    }

    public URLModel(String page, boolean flag) {
        this.page = page;
        this.flag = flag;
    }

    public URLModel(String s, Map<String, String> data, boolean b) {
        this.page = s;
        this.data = data;
        this.flag = b;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "URLModel [page=" + page + ", flag=" + flag + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (flag ? 1231 : 1237);
        result = prime * result + ((page == null) ? 0 : page.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        URLModel other = (URLModel) obj;
        if (flag != other.flag)
            return false;
        if (page == null) {
            if (other.page != null)
                return false;
        } else if (!page.equals(other.page))
            return false;
        return true;
    }
}