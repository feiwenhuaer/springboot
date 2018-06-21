package ocean.config;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Spring Boot集成MyBatis打包成jar时，找不到类的问题
 * 默认vfs(乱码)：org.apache.ibatis.io.DefaultVFS.list:102 -Reader entry: ����   2 �
 * @author xieyi
 */
public class SpringBootVfs extends VFS {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    protected List<String> list(URL url, String path) throws IOException {
        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] resources = resolver.getResources(path + "/**/*.class");
        List<Resource> resources1 = Arrays.asList(resources);
        List<String> resourcePaths = new ArrayList<String>();
        for (Resource resource : resources1) {
            resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
        }
        return resourcePaths;
    }

    private static String preserveSubpackageName(final URI uri, final String rootPath) {
        final String uriStr = uri.toString();
        final int start = uriStr.indexOf(rootPath);
        return uriStr.substring(start, uriStr.length());
    }

}