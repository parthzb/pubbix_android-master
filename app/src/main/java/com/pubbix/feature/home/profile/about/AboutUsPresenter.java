package com.pubbix.feature.home.profile.about;

import com.pubbix.base.BasePresenter;

import javax.inject.Inject;

public class AboutUsPresenter extends BasePresenter<AboutUsFragment, AboutUsContract.Interactor>
        implements AboutUsContract.Presenter {

    private AboutUsFragment view;
    private AboutUsContract.Router router;

    @Inject
    AboutUsPresenter(AboutUsFragment view, AboutUsContract.Router router) {
        this.view = view;
        this.router = router;
        this.router.setContext(view.getContext());
    }

    @Override
    public void setUpBricks() {
        view.addLogoBrick();
        view.addMissionStatementBrick();
        view.addAppVersionBrick();
        view.addContactBrick();
        view.addWebsiteBrick();
        view.addFacebookBrick();
        view.addTwitterBrick();
        view.addInstagramBrick();
        view.addYoutubeBrick();
        view.addPlayStoreBrick();
    }

    @Override
    public void onClickContactUs() {
        router.goToContactUsPage();
    }

    @Override
    public void onClickAdvertiseWithUs() {
        router.goToWebsite();
    }

    @Override
    public void onClickLikeUsOnFacebook() {
        router.goToFacebookPage();
    }

    @Override
    public void onClickFollowUsOnTwitter() {
        router.goToTwitterPage();
    }

    @Override
    public void onClickFollowUsInstagram() {
        router.goToInstagramPage();
    }

    @Override
    public void onClickWatchUsOnYoutube() {
        router.goToYoutubePage();
    }

    @Override
    public void onClickVisitPlayStore() {
        router.goToYoutubePage();
    }

}
