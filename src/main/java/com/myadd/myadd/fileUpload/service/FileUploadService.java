package com.myadd.myadd.fileUpload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {
    // @Value는 lombok 어노테이션이 아님에 주의!
    // 버켓 이름 동적 할당(properties에서 가져옴)
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String upload(MultipartFile multipartFile) throws IOException {
        // 파일 이름의 중복을 막기 위해 "UUID(랜덤 값) + 원본파일이름"로 연결함
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        // 파일 사이즈를 ContentLength를 이용하여 S3에 알려줌
        ObjectMetadata objMeta = new ObjectMetadata();
        // url을 클릭 시 사진이 웹에서 보이는 것이 아닌 바로 다운되는 현상을 해결하기 위해 메타데이터 타입 설정
        objMeta.setContentType(multipartFile.getContentType());
        InputStream inputStream = multipartFile.getInputStream();
        objMeta.setContentLength(inputStream.available());

        // 파일 stream을 열어서 S3에 파일을 업로드
        amazonS3.putObject(bucket, s3FileName, inputStream, objMeta);
        inputStream.close();
        // Url 가져와서 반환
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }
}
