package review.steam_game.service.admin;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import review.steam_game.dto.post.AdminPostRequestDto;
import review.steam_game.entity.Post.Image;
import review.steam_game.entity.Post.AdminPost;
import review.steam_game.repository.post.ImageRepository;
import review.steam_game.repository.post.AdminPostRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminFunctionService {
    private final AdminPostRepository adminPostRepository;
    private final ImageRepository imageRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String createPost(MultipartFile file, AdminPostRequestDto adminPostRequestDto) throws IOException {
        log.info("여기까지 돌아간다.");
        if (!file.isEmpty()) {
            //파일명을 얻어낼 수 있는 메소드
            String fileRealName = file.getOriginalFilename();
            log.info("여기가지도 돌아간다.");
            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            /*파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
            업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
            타언어를 지원하지 않는 환경에서는 정상 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		    고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 줍니다.*/
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            //바이트 파일로 이미지 변환 하기.
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            objectMetadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);

            //저장될 파일 이름
            String saveFileName = uniqueName + fileExtension;
            log.info(saveFileName);

            //S3에 저장
            amazonS3Client.putObject(new PutObjectRequest(bucketName, saveFileName, byteArrayIS, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            //이미지 url 받아오기
            String imageUrl = amazonS3Client.getUrl(bucketName, saveFileName).toString();
            //db에 이미지 저장하기
            Image image = new Image(saveFileName, imageUrl);
            imageRepository.save(image);
            //db에 포스트 저장하기
            AdminPost adminPost = new AdminPost(adminPostRequestDto, image);
            adminPostRepository.save(adminPost);
            return imageUrl;
        }
        return "게시물 등록에 실패 하였습니다.";
    }
    public String updatePost(Long postId, MultipartFile file, AdminPostRequestDto adminPostRequestDto) throws IOException {
        if (!file.isEmpty()) {
            //파일명을 얻어낼 수 있는 메소드
            String fileRealName = file.getOriginalFilename();

            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            /*파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
            업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
            타언어를 지원하지 않는 환경에서는 정상 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		    고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 줍니다.*/
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String uniqueName = uuids[0];

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            //바이트 파일로 이미지 변환 하기.
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            objectMetadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);

            //저장될 파일 이름
            String saveFileName = uniqueName + fileExtension;

            //S3에 저장
            amazonS3Client.putObject(new PutObjectRequest(bucketName, saveFileName, byteArrayIS, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            //이미지 url 받아오기
            String imageUrl = amazonS3Client.getUrl(bucketName, saveFileName).toString();
            AdminPost adminPost = adminPostRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("일치하는 아이디가 없습니다.")
            );
            adminPost.getImage().update(saveFileName, imageUrl);
            adminPost.update(adminPostRequestDto);
            return "수정 성공";
        }
        return "수정 실패";
    }

    public String deletePost(Long postId) {
        //이미지 삭제하기
        amazonS3Client.deleteObject(bucketName, adminPostRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일치하는 아이디가 없습니다.")
        ).getImage().getImageName());
        //게시글 삭제하기
        adminPostRepository.deleteById(postId);
        return "삭제가 완료되었습니다.";
    }
}
