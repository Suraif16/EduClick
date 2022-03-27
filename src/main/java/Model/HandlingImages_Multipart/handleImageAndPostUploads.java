package Model.HandlingImages_Multipart;

import Model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class handleImageAndPostUploads {

    public static EducationalWork uploadEPostImages(HttpServletRequest request, String path, LocalDate localDate, LocalTime localTime , String userId ) {

        String type = "";
        String message = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        String imageStatus = "";
        String classroomId = "";

        try {

            List<FileItem> files = servletFileUpload.parseRequest(request);

            FileItem imageFile = null;

            for (FileItem file : files) {

                if (file.isFormField()) {

                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);

                    if ( file.getFieldName().equals("message") ) {

                        message = new String(bytes);

                    } else if ( file.getFieldName().equals("type") ) {

                        type = new String(bytes);

                    }else if ( file.getFieldName().equals( "classroomId" ) ){

                        classroomId = new String(bytes);

                    }

                    inputStream.close();


                } else {

                    imageFile = file;

                }

            }

            if ( imageFile == null ){

                imageStatus = "false";

            }else{

                imageStatus = "true";

            }

            EducationalWork educationalWork = new EducationalWork( message , type , localDate , localTime , imageStatus );

            educationalWork = educationalWork.insertEducationalWork( imageFile , path , classroomId );
            
            Classroom classroom = new Classroom();
            ArrayList<String> studentList = classroom.getStudentListInClass( classroomId );

            Notifications notifications = new Notifications();
            notifications.insertEpostNotificationsFromTeacher( userId , studentList , classroomId , "Educational Post" );

            return educationalWork;


        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    public static Answers uploadEPostAnswersImages(HttpServletRequest request , String path , LocalDate localDate , LocalTime localTime , String userId ){

        String answer = "";
        String epostId = "";
        String imageStatus = "";
        String classroomId = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );

        try{

            List<FileItem> files = servletFileUpload.parseRequest( request );

            FileItem imageFile = null;

            for (FileItem file : files ){

                if ( file.isFormField() ){

                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[ inputStream.available() ];
                    inputStream.read( bytes );

                    if ( file.getFieldName().equals( "answers" ) ){

                        answer = new String( bytes );

                    }else if(file.getFieldName().equals( "classroomId" )){

                        classroomId = new String(bytes);

                    }

                    else if ( file.getFieldName().equals( "ePostId" ) ){

                        epostId = new String( bytes );
                        System.out.println("Notification check in File upload file :"+epostId);
                        Post post = new Post();
                        System.out.println("The classroomID of notiii is : "+post.selectClassroomId(epostId));
                        Classroom classroom = new Classroom();
                        String teacherID = classroom.getClassroomOwnerId(post.selectClassroomId(epostId));

                        System.out.println("The userID to notification syste,m is : "+teacherID);



                        HttpSession session = request.getSession( false );

                        User user = (User) session.getAttribute("User");

                        String studentId = user.getUserId();

                        String param = "Answer";

                        System.out.println("The Post ID not notification : "+epostId);
                        System.out.println("The teacher ID not notification : "+teacherID);
                        System.out.println("The stundet not notification : "+studentId);

                        Notifications notifications = new Notifications();
                        notifications.insertNotifications(studentId,teacherID,classroomId,param);

                    }

                    inputStream.close();


                }else{

                    imageFile = file;

                }
                if ( imageFile == null ){

                    imageStatus = "false";

                }else{

                    imageStatus = "true";

                }

            }
            System.out.println("Answer is : "+answer);
            System.out.println("EPostID is : "+epostId);
            System.out.println("ImageStatus is : "+imageStatus);
            System.out.println("ClassroomId in notification insert : "+classroomId);

            Answers answers = new Answers( answer , epostId , localDate , localTime , userId ,imageStatus);

            answers.setAnswer(answer);
            answers.setQuestionId(epostId);
            answers.setImageStatus(imageStatus);

            return answers.insertAnswers( imageFile , path );


        }catch ( Exception e ){

            System.out.println( e );

        }

        return null;

    }

    public static NewsFeeds uploadNewsFeedsImages(HttpServletRequest request , String path , LocalDate localDate , LocalTime localTime ){

        String message = "";
        String imageStatus = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );

        try{

            List<FileItem> files = servletFileUpload.parseRequest( request );

            FileItem imageFile = null;

            for (FileItem file : files ){

                if ( file.isFormField() ){

                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[ inputStream.available() ];
                    inputStream.read( bytes );

                    if ( file.getFieldName().equals( "message" ) ){

                        message = new String( bytes );

                    }

                    inputStream.close();


                }else{

                    imageFile = file;

                }
                if ( imageFile == null ){

                    imageStatus = "false";

                }else{

                    imageStatus = "true";

                }

            }

            NewsFeeds newsFeeds = new NewsFeeds( message , localDate , localTime,imageStatus);
            newsFeeds.setImageStatus(imageStatus);

            /*HttpSession session = request.getSession( false );
            User user = (User) session.getAttribute("User");

            String userId = user.getUserId();
            Requests requests = new Requests();

            ArrayList<String> followersList = requests.getTeacherFollowers(userId);
            ArrayList<String> friendList = requests.getTeacherFriends(userId);
            ArrayList<String> notifyList = new ArrayList<>();
            notifyList.addAll(friendList);
            notifyList.addAll(followersList);

            String param = "Posted";

            String postId = "";

            Notifications notifications = new Notifications();
            notifications.insertEpostNotificationsFromTeacher(userId,notifyList,postId,param);*/

            newsFeeds =  newsFeeds.insertNewsFeeds( imageFile , path );
            return newsFeeds;



        }catch ( Exception e ){

            System.out.println( e );

        }

        return null;

    }

    public static AdminWork uploadEPostImages(HttpServletRequest request, String path, LocalDate localDate, LocalTime localTime ) {
        String message = "";
        String status = "0";
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());

        try {
            List<FileItem> files = servletFileUpload.parseRequest(request);
            FileItem imageFile = null;
            for (FileItem file : files) {
                if (file.isFormField()) {
                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    if ( file.getFieldName().equals("message") ) {
                        message = new String(bytes);
                    }
                    inputStream.close();
                } else {
                    imageFile = file;
                    status = "1";
                }
            }

            AdminWork adminWork = new AdminWork ( message ,  localDate , localTime , status);
            return adminWork.insertAdminPostWork( imageFile , path );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void uploadProfileImage(HttpServletRequest request, String path, String userId ) {

        User user = new User( userId );
        String workPlace = null;
        String userProfilePictureStatus = null;
        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );

        try {

            List<FileItem> files = servletFileUpload.parseRequest( request );

            FileItem imageFile = null;

            for (FileItem file : files) {

                if ( file.isFormField() ) {

                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[ inputStream.available() ];
                    inputStream.read(bytes);

                    if ( file.getFieldName().equals("firstName") ) {

                        user.setFirstName( new String(bytes) );

                    } else if ( file.getFieldName().equals("lastName") ) {

                        user.setLastName( new String(bytes) );

                    }else if ( file.getFieldName().equals( "country" ) ){

                        user.setCountry( new String(bytes) );

                    }else if ( file.getFieldName().equals( "city" ) ){

                        user.setCity( new String(bytes) );

                    }else if ( file.getFieldName().equals( "workPlace" ) ){

                        workPlace = new String(bytes);

                    }else if ( file.getFieldName().equals( "userProfilePictureStatus" ) ){

                        userProfilePictureStatus = new String(bytes);

                    }else if ( file.getFieldName().equals( "mobileNumber" ) ){

                        user.setMobileNumber( new String(bytes) );

                    }else if ( file.getFieldName().equals( "countryCode" ) ){

                        user.setCountryCode( new String(bytes) );

                    }

                    inputStream.close();


                } else {

                    imageFile = file;

                }

            }

            if ( imageFile != null ){

                File file = new File( path + "Resources\\Images\\UserProfileImages\\profilePicture" + userId + ".jpeg" );
                System.out.println(file.delete());
                user.setProfilePicture( userId );

                Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( "profilePicture" + userId , path + "Resources\\Images\\UserProfileImages\\" , imageFile );
                saveImage.start();


            }else{

                if ( userProfilePictureStatus.equals( "true" ) ){

                    user.setProfilePicture( null );

                }else{

                    user.setProfilePicture( userId );

                }


            }

            user.updateUserDetails( workPlace );



        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}