package com.pubbix.feature.home.listingdetail;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.pubbix.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Call To generate marker
 * <p>
 * LatLng tmpLatLng = new LatLng(22.561718, 70.422364);
 * generateMarkerFromResource(tmpLatLng, "Tryyy", R.drawable.map_green);
 * <p>
 * <p>
 * Used for getfocus on LatLan..
 * <p>
 * showLocationFocus(tmpLatLng);
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    public static final int[] mapTypes = {
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID
    };
    public static final String ARGUMENT_MAP_TYPE_CHANGABLE = "map_type";
    static final float ZOOM_LEVEL = 11f;
    private static long duration = 500;
    private static long durationRotation = 500;
    MapView mapView;
    ProgressBar progressMap;
    GoogleMap m_GoolgeMap;
    List<Marker> m_MarkerList;
    MapCallBack m_MapCallBack;
    int currentMapStyle = 0;


    private List<Polyline> m_RouteLine;
    private boolean isMarkerRotating = false;

    public MapFragment() {
        // Required empty public constructor
    }


    public static String getOriginParams(LatLng origin) {
        StringBuilder params = new StringBuilder();
        if (null != origin) {
            params.append(origin.latitude).append(",").append(origin.longitude);
        }
        return params.toString();
    }

    public static String getDestinationParams(LatLng destinaion) {
        StringBuilder params = new StringBuilder();
        if (null != destinaion) {
            params.append(destinaion.latitude).append(",").append(destinaion.longitude);
        }
        return params.toString();
    }

    public void setMapCallBack(MapCallBack mapCallBack) {
        m_MapCallBack = mapCallBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = view.findViewById(R.id.mapView);
        progressMap = view.findViewById(R.id.progress_map);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        checkPermission(googleMap);
    }

    /**
     * Check permission if permission granted then inti call otherwise nothing doing...
     *
     * @param googleMap :- that generated in onMapReady state...
     */
    public void checkPermission(final GoogleMap googleMap) {
        m_GoolgeMap = googleMap;
        init();

    }

    private void init() {
        m_GoolgeMap.getUiSettings().setRotateGesturesEnabled(false);
        m_GoolgeMap.getUiSettings().setMapToolbarEnabled(false);
        clearMap();

        mapView.onResume();
        m_MapCallBack.callbackReady();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // get angle to rotate...
    private double bearingBetweenLocations(LatLng latLng1, LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }

    private void rotateMarker(final Marker marker, final float toRotation) {
        if (!isMarkerRotating) {
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final float startRotation = marker.getRotation();
            final Interpolator interpolator = new LinearInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    isMarkerRotating = true;

                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = interpolator.getInterpolation((float) elapsed / durationRotation);

                    float rot = t * toRotation + (1 - t) * startRotation;

                    marker.setRotation(-rot > 180 ? rot / 2 : rot);
                    if (t < 1.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    } else {
                        isMarkerRotating = false;
                    }
                }
            });
        }
    }

    public void rotateMarker(Marker marker, LatLng toPostion) {
        LatLng currentPos = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);

        rotateMarker(marker, (float) bearingBetweenLocations(currentPos, toPostion));
    }

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = m_GoolgeMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                LatLng nextPostion = new LatLng(lat, lng);
                marker.setPosition(nextPostion);

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.post(this);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }


    public void showMyLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        m_GoolgeMap.setMyLocationEnabled(true);
    }


    public void showLocationFocus(LatLng location) throws NullPointerException {
        if (m_GoolgeMap == null) return;
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, ZOOM_LEVEL);
        m_GoolgeMap.animateCamera(cameraUpdate);
    }

    public Marker generateMarkerFromResource(LatLng markerLocation, String setMarkerData, BitmapDescriptor markerIcon/*, int resource*/) {
        //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(resource);
        Marker marker = m_GoolgeMap.addMarker(new MarkerOptions()
                .position(markerLocation)
                .title(setMarkerData)
                .icon(markerIcon)
                .snippet(setMarkerData));

        /*.icon(icon));*/
        m_MarkerList.add(marker);
        return marker;
    }

    public void animateWithRotate(Marker marker, LatLng nextPos) {
        animateMarker(marker, nextPos, false);
        rotateMarker(marker, nextPos);
    }

    public void removeAllMarker() {
        if (null != m_MarkerList) {
            for (Marker marker : m_MarkerList) {
                marker.remove();
            }
            m_MarkerList.clear();
        } else {
            m_MarkerList = new ArrayList<>();
        }
    }

    public void removeAllRoute() {
        if (null != m_RouteLine) {
            for (Polyline marker : m_RouteLine) {
                marker.remove();
            }
            m_RouteLine.clear();
        } else {
            m_RouteLine = new ArrayList<>();
        }
    }

    public void clearMap() {
        if (m_GoolgeMap != null) {
            removeAllMarker();
            removeAllRoute();
            m_GoolgeMap.clear();
        }
    }

    /**
     * generate Rout with waypoints...
     */
    public String getWarpaintParams(LatLng... waypoints) {
        StringBuilder params = new StringBuilder();
        if (null != waypoints && waypoints.length > 0) {
            for (int i = 0; i < waypoints.length; i++) {
                if (null != waypoints[i]) {
                    if (i != 0)
                        params.append("|");
                    params.append("via:").append(waypoints[i].latitude).append(",").append(waypoints[i].longitude);
                }
            }
        }
        return params.toString();
    }

    private void changeMapStyle() {
        if (null != m_GoolgeMap) {
            currentMapStyle = currentMapStyle + 1;
            if (mapTypes.length <= currentMapStyle) {
                currentMapStyle = 0;
            }
            m_GoolgeMap.setMapType(mapTypes[currentMapStyle]);
        }
    }

    public interface GetDistanceDurationCallBack {
        /*void getRouteDistance(DistanceModel distanceModel);

        void getRouteDuration(DistanceModel durationModel);*/
    }

    public interface MapCallBack {
        void callbackReady();
    }
}
