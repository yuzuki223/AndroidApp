package jp.ac.meijou.android.s221205037;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s221205037.databinding.ActivityConnectivityBinding;

public class ConnectivityActivity extends AppCompatActivity {

    private ActivityConnectivityBinding binding;
    private ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddresses(currentNetwork);

    }

    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);
        if(caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            }else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                transport = "WiFi";
            } else {
                transport = "その他";
            }
            binding.transport.setText(transport);
        }
    }

    private void updateIpAddresses(Network network) {
        var linkProps = connectivityManager.getLinkProperties(network);
        if(linkProps != null) {
            var addresses = linkProps.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.ipaddress.setText(addresses);
        }
    }
}