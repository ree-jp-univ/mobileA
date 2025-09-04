package jp.ac.meijou.a241205084;

import java.util.Map;

public class Gist {
    public Map<String, GistFile> files;
    public static class GistFile {
        public String content;
    }
}
