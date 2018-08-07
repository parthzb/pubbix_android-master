package com.pubbix.feature.home;

import android.support.v4.app.Fragment;

import com.pubbix.feature.home.favorite.FavoriteComponent;
import com.pubbix.feature.home.favorite.FavoriteFragment;
import com.pubbix.feature.home.homepage.HomepageComponent;
import com.pubbix.feature.home.homepage.HomepageFragment;
import com.pubbix.feature.home.inbox.InboxComponent;
import com.pubbix.feature.home.inbox.InboxFragment;
import com.pubbix.feature.home.listingdetail.ListingDetailComponent;
import com.pubbix.feature.home.listingdetail.ListingDetailFragment;
import com.pubbix.feature.home.profile.ProfileComponent;
import com.pubbix.feature.home.profile.ProfileFragment;
import com.pubbix.feature.home.profile.about.AboutUsComponent;
import com.pubbix.feature.home.profile.about.AboutUsFragment;
import com.pubbix.feature.home.profile.edit.EditProfileComponent;
import com.pubbix.feature.home.profile.edit.EditProfileFragment;
import com.pubbix.feature.home.profile.edit.generic.GenericProfileEditComponent;
import com.pubbix.feature.home.profile.edit.generic.GenericProfileEditFragment;
import com.pubbix.feature.home.profile.publicprofile.PublicProfileComponent;
import com.pubbix.feature.home.profile.publicprofile.PublicProfileFragment;
import com.pubbix.feature.home.profile.publicprofile.favorites.FavoritesListingComponent;
import com.pubbix.feature.home.profile.publicprofile.favorites.FavoritesListingFragment;
import com.pubbix.feature.home.profile.publicprofile.listing.ListingComponent;
import com.pubbix.feature.home.profile.publicprofile.listing.ListingFragment;
import com.pubbix.feature.home.profile.publicprofile.reviews.UserReviewsComponent;
import com.pubbix.feature.home.profile.publicprofile.reviews.UserReviewsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        HomepageComponent.class,
        FavoriteComponent.class,
        InboxComponent.class,
        ProfileComponent.class,
        AboutUsComponent.class,
        PublicProfileComponent.class,
        ListingComponent.class,
        UserReviewsComponent.class,
        FavoritesListingComponent.class,
        EditProfileComponent.class,
        GenericProfileEditComponent.class,
        ListingDetailComponent.class,
})
public abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(HomepageFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomepageInjector(HomepageComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(FavoriteFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFavoriteInjector(FavoriteComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(InboxFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindInboxInjector(InboxComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ProfileFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindProfileInjector(ProfileComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(AboutUsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindAboutUsInjector(AboutUsComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PublicProfileFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindPublicProfileInjector(PublicProfileComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(FavoritesListingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindFavoritesListingInjector(FavoritesListingComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ListingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindPublishedListingtInjector(ListingComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserReviewsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserReviewsInjector(UserReviewsComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(EditProfileFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindEditProfileInjector(EditProfileComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(GenericProfileEditFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindGenericProfileEditInjector(GenericProfileEditComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ListingDetailFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindListingDetailInjector(ListingDetailComponent.Builder builder);
}
