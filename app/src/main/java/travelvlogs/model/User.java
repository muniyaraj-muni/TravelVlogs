package travelvlogs.model;

import android.net.Uri;

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    String name;
    String email  ;
    Uri photoUrl;

    public User(String name, String email, Uri photoUrl){
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public User(){

    }
}
