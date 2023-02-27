package my.numb.data.repository

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import my.numb.domain.repository.NetworkStatusRepository

class NetworkStatusRepositoryImpl(
    private val connectivityManager: ConnectivityManager
) : NetworkStatusRepository {

    override fun getNetworkStatus(): Flow<Boolean> = channelFlow {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        trySend(false)
        val setOfNetworks = HashSet<String>()
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                if (setOfNetworks.isEmpty()) {
                    trySend(true)
                }
                setOfNetworks += network.toString()
            }

            override fun onLost(network: Network) {
                setOfNetworks -= network.toString()
                if (setOfNetworks.isEmpty()) {
                    trySend(false)
                }
            }
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}