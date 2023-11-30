# 기본 이미지
FROM openjdk:8-jre
# 작성자
MAINTAINER wenbin
# JAR 파일을 경로로 복사
ADD intej-memo.jar intej-memo.jar
# 시간 설정
RUN ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
RUN echo 'Asia/Seoul' >/etc/timezone

# 서비스 시작
ENTRYPOINT ["java","-jar","/intej-memo.jar"]
# 포트 노출
EXPOSE 8090
