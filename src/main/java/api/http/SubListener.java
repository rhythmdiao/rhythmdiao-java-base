package api.http;

import com.google.common.eventbus.Subscribe;

public interface SubListener {
    @Subscribe
    void listen(String msg);
}
