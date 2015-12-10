package com.rhythmdiao.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ClientBuilder {
    private PoolingHttpClientConnectionManager connectionManager;

    public ClientBuilder() {
        try {
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            return true;
                        }
                    }).build()))
                    .build();

            connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setMaxTotal(1);
            connectionManager.setDefaultMaxPerRoute(1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public ClientBuilder setMaxTotal(int maxTotal) {
        connectionManager.setMaxTotal(maxTotal);
        return this;
    }

    public ClientBuilder setDefaultMaxPerRoute(int maxPerRoute) {
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        return this;
    }

    public CloseableHttpClient getClient() {
        return build();
    }

    private CloseableHttpClient build() {
        HttpClientBuilder clientBuilder = HttpClients.custom().setConnectionManager(connectionManager);

        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5000).setTcpNoDelay(true).build();

        clientBuilder.setDefaultSocketConfig(socketConfig);
        clientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        return clientBuilder.build();
    }
}
