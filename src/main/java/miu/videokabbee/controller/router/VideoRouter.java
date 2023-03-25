package miu.videokabbee.controller.router;



public  class VideoRouter {


    public static final String videoList= "/videoList";   //getting all videos
   public static final String videoId= "/{id}";       //getting videos by Id
   public static final String videoTitle = "title/{title}";     // getting videos by Title
   public static final String videoUrl = "url/{url}";         // getting videos by Url
   public static final String newVideo = "/add";           // posting/adding new Video

}
