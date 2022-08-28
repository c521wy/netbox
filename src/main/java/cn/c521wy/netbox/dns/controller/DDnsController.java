package cn.c521wy.netbox.dns.controller;

import cn.c521wy.netbox.common.util.InetUtils;
import cn.c521wy.netbox.common.util.WebUtils;
import com.aliyun.alidns20150109.models.UpdateDomainRecordRequest;
import com.aliyun.alidns20150109.models.UpdateDomainRecordResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;


@RestController
@RequestMapping("/ddns/")
@Slf4j
public class DDnsController {

    public static com.aliyun.alidns20150109.Client createClient(String regionId, String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint("alidns." + regionId + ".aliyuncs.com");
        return new com.aliyun.alidns20150109.Client(config);
    }

    @PostMapping("/update/v1")
    public ResponseEntity<?> update(HttpServletRequest request,
                                    @RequestHeader("X-REGION-ID") String regionId,
                                    @RequestHeader("X-ACCESS-KEY-ID") String accessKeyId,
                                    @RequestHeader("X-ACCESS-KEY-SECRET") String accessKeySecret,
                                    // 目标域名
                                    String domain,
                                    String recordId,
                                    String rr,
                                    String type) throws Exception {

        String currentIp = null;

        try {
            currentIp = InetUtils.resolveIp(domain);
            log.info("domain[{}] resolved to [{}]", domain, currentIp);
        } catch (UnknownHostException e) {
            // ignore
        }

        String remoteIp = WebUtils.getRemoteIp(request);

        if (StringUtils.equals(currentIp, remoteIp)) {
            return ResponseEntity.ok("ip not change, skipped");
        }

        log.info("updating RecordId[{}] to IP[{}]", recordId, remoteIp);

        UpdateDomainRecordRequest req = new UpdateDomainRecordRequest();
        req.setRecordId(recordId);
        req.setRR(rr);
        req.setType(type);
        req.setValue(remoteIp);

        UpdateDomainRecordResponse resp = createClient(regionId, accessKeyId, accessKeySecret).updateDomainRecord(req);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/ip/v1")
    public ResponseEntity<?> ip(HttpServletRequest request) {
        return ResponseEntity.ok(WebUtils.getRemoteIp(request));
    }

}
