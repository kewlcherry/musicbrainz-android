/*
 * Copyright (C) 2011 Jamie McDonald
 * 
 * This file is part of MusicBrainz for Android.
 * 
 * MusicBrainz for Android is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 * 
 * MusicBrainz for Android is distributed in the hope that it 
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MusicBrainz for Android. If not, see 
 * <http://www.gnu.org/licenses/>.
 */

package org.musicbrainz.mobile.loader;

import java.io.IOException;

import org.musicbrainz.android.api.MusicBrainz;
import org.musicbrainz.android.api.webservice.Entity;
import org.musicbrainz.android.api.webservice.MusicBrainzWebClient;
import org.musicbrainz.mobile.MusicBrainzApp;
import org.musicbrainz.mobile.loader.result.AsyncResult;
import org.musicbrainz.mobile.loader.result.LoaderStatus;

import android.support.v4.content.AsyncTaskLoader;

public class SubmitRatingLoader extends AsyncTaskLoader<AsyncResult<Float>> {

    private Entity type;
    private String mbid;
    private int rating;

    public SubmitRatingLoader(Entity type, String mbid, int rating) {
        super(MusicBrainzApp.getContext());
        this.type = type;
        this.mbid = mbid;
        this.rating = rating;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public AsyncResult<Float> loadInBackground() {
        MusicBrainz client = new MusicBrainzWebClient(MusicBrainzApp.getCredentials());
        try {
            client.submitRating(type, mbid, rating);
            Float updatedRating = client.lookupRating(type, mbid);
            return new AsyncResult<Float>(LoaderStatus.SUCCESS, updatedRating);
        } catch (IOException e) {
            return new AsyncResult<Float>(LoaderStatus.EXCEPTION, e);
        }
    }
}
