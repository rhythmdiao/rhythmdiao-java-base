package com.rhythmdiao.handler;

import com.google.common.cache.Cache;
import com.rhythmdiao.config.IPAccessCfg;
import com.rhythmdiao.util.cache.CacheWrapper;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.event.ReloadEvent;
import org.aeonbits.owner.event.ReloadListener;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.server.AbstractHttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlackListHandler extends AbstractHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BlackListHandler.class);
    private IPAccessCfg cfg = ConfigCache.get("ip_access_config");

    public void init() {
        cfg.addReloadListener(new ReloadListener() {
            @Override
            public void reloadPerformed(ReloadEvent reloadEvent) {
                Cache<String, String[]> cache = CacheWrapper.getInstance().getOrDefault("ip_access_config");
                cache.put("forbidden", cfg.forbidden());
                LOG.debug("Reload performed......");
            }
        });
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AbstractHttpConnection connection = baseRequest.getConnection();
        if (connection != null) {
            EndPoint endp = connection.getEndPoint();
            if (endp != null) {
                String addr = endp.getRemoteAddr();
                String[] forbidden = ((String[]) CacheWrapper.getInstance().getOrDefault("ip_access_config").getIfPresent("forbidden"));
                if (forbidden == null) {
                    cfg.reload();
                    forbidden = cfg.forbidden();
                }
                if (addr != null && isForbidden(addr, forbidden)) {
                    response.setStatus(HttpStatus.FORBIDDEN_403);
                    baseRequest.setHandled(true);
                }
            }
        }
    }

    private boolean isForbidden(String addr, String[] forbidden) {
        if (forbidden == null) return false;
        if (forbidden[0].equals("*")) return false;
        for (String item : forbidden) {
            if (addr.equals(item)) return true;
        }
        return false;
    }
}
