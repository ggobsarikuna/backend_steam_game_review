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
import review.steam_game.config.security.UserDetailsImpl;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.Post;
import review.steam_game.repository.post.PostRepository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminFunctionService {
    private final PostRepository postRepository;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.S3.bucket}")
    private String bucketName;
    public String createPost(PostRequestDto postRequestDto)throws IOException{

        //EC2에 저장하는 로직

//        //form-data 형식으로 가져온 파일
//        MultipartFile file = postRequestDto.getFile();
//        //file이 비어있지 않다면
//        if(!file.isEmpty()){
//            String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메소드
//            long size = file.getSize(); //파일 사이즈
//            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
//            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
//            String uploadFolder = "C:\\test\\upload";
//            /*
//		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
//		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
//		  타언어를 지원하지 않는 환경에서는 정상 동작이 되지 않습니다.(리눅스가 대표적인 예시)
//		  고유한 랜덤 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 줍니다.
//		 */
//
//            UUID uuid = UUID.randomUUID();
//            String[] uuids = uuid.toString().split("-");
//
//            String uniqueName = uuids[0];
//
//            //uuid 적용후 저장.
//            File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);
//            try {
//                file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Post post = new Post(postRequestDto, uniqueName);
//            postRepository.save(post);
//            System.out.println(post);
//            return post;
//
////            String path = Paths.get("").toAbsolutePath() + "/upload/shop/";
////            File folder = new File(path);
////            if (!folder.exists()) {
////                folder.mkdirs();
////            }
////            String filePath = path + file.getOriginalFilename();
////            file.transferTo(new File(filePath));
//        }
//        return null;

        //S3에 저장하는 로직

        //form-data 형식으로 가져온 파일
        MultipartFile file = postRequestDto.getFile();
        //file이 비어있지 않다면
        if(!file.isEmpty()){
            //파일명을 얻어낼 수 있는 메소드
            String fileRealName = file.getOriginalFilename();

            //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
            String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());

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
            Post post = new Post(postRequestDto, imageUrl);
            postRepository.save(post);
            return "게시물이 등록 되었습니다.";
        }
        return "게시물 등록에 실패 하였습니다.";
    }

    public Post updatePost(PostRequestDto postRequestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일치하는 아이디가 없습니다.")
        );
        post.update(postRequestDto);
        return post;
    }

    public String deletePost(Long postId) {
        postRepository.deleteById(postId);
        return "삭제가 완료되었습니다.";
    }
}
