package com.pubbix.data.remote;

import com.pubbix.data.model.Address;
import com.pubbix.data.model.Category;
import com.pubbix.data.model.Credentials;
import com.pubbix.data.model.HomepageListings;
import com.pubbix.data.model.Listings;
import com.pubbix.data.model.PopularKeywords;
import com.pubbix.data.model.Profile;
import com.pubbix.data.model.Status;
import com.pubbix.data.model.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by AugusteC on 3/26/17.
 */

public interface PubbixApi {

    String LISTING_PATH = "listings";

    String POPULAR_KEYWORDS_PATH = "listings/popularKeywords";

    String CATEGORY_PATH = "categories";

    String HOMEPAGE_LISTING_PATH = "listings/homepage";

    String USER_LISTING_PATH = "listings/{userId}";

    String USER_PUBLISHED_LISTING_PATH = "listings/published/{userId}";

    String USER_UNPUBLISHED_LISTING_PATH = "listings/unpublished/{userId}";

    String USER_REGISTRATION_PATH = "user";

    String USER_DETAILS_PATH = "user/{userId}";

    String USER_UPDATE_PROFILE_PATH = "user/update";

    String USER_UPDATE_PROFILE_IMAGE_PATH = "user/update/profilePicture";

    String USER_UPDATE_FULL_NAME_PATH = "user/update/name";

    String USER_UPDATE_EMAIL_PATH = "user/update/email";

    String USER_UPDATE_PHONE_PATH = "user/update/phone";

    String USER_UPDATE_GENDER_PATH = "user/update/gender";

    String USER_UPDATE_LOCATION_PATH = "user/update/location";

    String USER_UPDATE_BIRTHDAY_PATH = "user/update/birthday";

    String USER_UPDATE_BIOGRAPHY_PATH = "user/update/biography";

    String USER_UPDATE_COMPANY_IDENTITY_PATH = "user/update/company/identity";

    String USER_LOGIN_BY_PHONE_PATH = "user/login/phone";

    String USER_LOGIN_BY_EMAIL_PATH = "user/login/email";

    String USER_LOGIN_BY_FACEBOOK_PATH = "user/login/facebook";


    @GET(LISTING_PATH)
    Observable<Listings> getLatestListings();

    @GET(HOMEPAGE_LISTING_PATH)
    Observable<HomepageListings> getHomepageListings();

    @GET(POPULAR_KEYWORDS_PATH)
    Observable<List<PopularKeywords>> getPopularKeywords();

    @GET(CATEGORY_PATH)
    Observable<List<Category>> getCategories();

    @GET(USER_LISTING_PATH)
    Observable<Listings> getUserLatestListings(@Path("userId") String userId);

    @GET(USER_PUBLISHED_LISTING_PATH)
    Observable<Listings> getUserPublishedListings(@Path("userId") String userId);

    @GET(USER_UNPUBLISHED_LISTING_PATH)
    Observable<Listings> getUserUnpublishedListings(@Path("userId") String userId);

    @POST(USER_REGISTRATION_PATH)
    Single<Profile> createUserAccount(@Body Credentials credentials);

    @GET(USER_DETAILS_PATH)
    Observable<Profile> getUserDetails(@Path("userId") String userId);

    @POST(USER_UPDATE_PROFILE_PATH)
    Observable<Profile> updateUserProfile(@Body User user);

    @POST(USER_UPDATE_PROFILE_IMAGE_PATH)
    @FormUrlEncoded
    Observable<Profile> updateProfileImage(@Field("userId") String userId, @Field("profilePicture") String profileImage);

    @POST(USER_UPDATE_FULL_NAME_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserFullName(@Field("userId") String userId, @Field("fullName") String fullName);

    @POST(USER_UPDATE_EMAIL_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserEmail(@Field("userId") String userId, @Field("email") String email);

    @POST(USER_UPDATE_PHONE_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserPhoneNumber(@Field("userId") String userId, @Field("phoneNumber") String phoneNumber);

    @POST(USER_UPDATE_GENDER_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserGender(@Field("userId") String userId, @Field("gender") int gender);

    @POST(USER_UPDATE_LOCATION_PATH)
    Observable<Profile> updateUserLocation(@Body Address address);


    @POST(USER_UPDATE_BIRTHDAY_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserBirthday(@Field("userId") String userId, @Field("dayOfBirth") String birthday);

    @POST(USER_UPDATE_BIOGRAPHY_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserBiography(@Field("userId") String userId, @Field("biography") String biography);

    @POST(USER_UPDATE_COMPANY_IDENTITY_PATH)
    @FormUrlEncoded
    Observable<Profile> updateUserCompanyIdentity(@Field("userId") String userId, @Field("identity") String companyIdentity);

    @POST(USER_LOGIN_BY_PHONE_PATH)
    @FormUrlEncoded
    Single<Profile> retrieveUserByPhone(@Field("phoneNumber") String phoneNumber);

    @POST(USER_LOGIN_BY_EMAIL_PATH)
    @FormUrlEncoded
    Single<Profile> retrieveUserByEmail(@Field("email") String email);

    @POST(USER_LOGIN_BY_FACEBOOK_PATH)
    @FormUrlEncoded
    Single<Profile> retrieveUserByFacebook(@Field("facebookId") String facebookId);

    @Multipart
    @POST(LISTING_PATH)
    Observable<Status> publishListing(
            @PartMap Map<String, RequestBody> listing,
            @Part List<MultipartBody.Part> pictures
    );
}
