package com.apelisser.algasensors.device.management.api.client.impl;

import com.apelisser.algasensors.device.management.api.client.RestClientFactory;
import com.apelisser.algasensors.device.management.api.client.SensorMonitoringClient;
import com.apelisser.algasensors.device.management.api.model.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

//@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClientFactory restClientFactory) {
        this.restClient = restClientFactory.temperatureMonitoringRestClient();
    }

    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient.put()
            .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
            .retrieve()
            .toBodilessEntity();
    }

    @Override
    public void disableMonitoring(TSID sensorId) {
        restClient.delete()
            .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
            .retrieve()
            .toBodilessEntity();
    }

    @Override
    public SensorMonitoringOutput getDetail(TSID sensorId) {
        return restClient.get()
            .uri("/api/sensors/{sensorId}/monitoring", sensorId)
            .retrieve()
            .body(SensorMonitoringOutput.class);
    }

}
