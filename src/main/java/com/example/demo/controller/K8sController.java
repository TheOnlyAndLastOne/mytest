package com.example.demo.controller;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhi.zhao
 * @date 2/19/2020 5:24 PM
 */
@RestController
@RequestMapping("/k8s")
public class K8sController {

    @RequestMapping("/getPods")
    public String getPods(){
        try {
            /*ApiClient client = new ClientBuilder().setBasePath("ApiServer地址").setVerifyingSsl(false)
                                                  .setAuthentication(new AccessTokenAuthentication("Token")).build();
            Configuration.setDefaultApiClient(client);*/

            // loading the in-cluster config, including:
            //   1. service-account CA
            //   2. service-account bearer-token
            //   3. service-account namespace
            //   4. master endpoints(ip, port) from pre-set environment variables
            ApiClient client = ClientBuilder.cluster().build();

            // set the global default api-client to the in-cluster one from above
            Configuration.setDefaultApiClient(client);

            // the CoreV1Api loads default api-client from global configuration.
            CoreV1Api api = new CoreV1Api();

            // invokes the CoreV1Api client
            V1PodList list =
                    api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            for (V1Pod item : list.getItems()) {
                System.out.println(item.getMetadata().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return "finish";
    }

}
