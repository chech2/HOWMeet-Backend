package org.chzzk.howmeet.domain.temporary.record.controller;

import static org.springframework.http.HttpStatus.CREATED;

import lombok.RequiredArgsConstructor;
import org.chzzk.howmeet.domain.common.auth.annotation.Authenticated;
import org.chzzk.howmeet.domain.temporary.auth.entity.Guest;
import org.chzzk.howmeet.domain.temporary.record.dto.post.request.GSRecordPostRequest;
import org.chzzk.howmeet.domain.temporary.record.service.GSRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/gs-record")
@RestController
public class GSRecordController {

    final GSRecordService gsRecordService;

    @GetMapping("/{gsId}")
    public ResponseEntity<?> getGSRecord(@PathVariable(value = "gsId") final Long gsId) {
        return ResponseEntity.ok(gsRecordService.getGSRecord(gsId));
    }

    @PostMapping
    public ResponseEntity<?> postGSRecord(@RequestBody final GSRecordPostRequest gsRecordPostRequest,
            @Authenticated final AuthPrincipal authPrincipal) {
        gsRecordService.postGSRecord(gsRecordPostRequest, authPrincipal);
        return ResponseEntity.ok(CREATED);
    }
}
