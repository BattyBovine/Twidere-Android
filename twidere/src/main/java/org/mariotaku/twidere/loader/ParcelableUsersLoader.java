/*
 * 				Twidere - Twitter client for Android
 * 
 *  Copyright (C) 2012-2014 Mariotaku Lee <mariotaku.lee@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import org.mariotaku.twidere.Constants;
import org.mariotaku.twidere.loader.iface.IExtendedLoader;
import org.mariotaku.twidere.model.ParcelableUser;
import org.mariotaku.twidere.util.collection.NoDuplicatesArrayList;

import java.util.Collections;
import java.util.List;

public abstract class ParcelableUsersLoader extends AsyncTaskLoader<List<ParcelableUser>>
        implements IExtendedLoader, Constants {

    private final List<ParcelableUser> mData = Collections
            .synchronizedList(new NoDuplicatesArrayList<ParcelableUser>());
    private boolean mFromUser;

    public ParcelableUsersLoader(final Context context, final List<ParcelableUser> data, boolean fromUser) {
        super(context);
        setFromUser(fromUser);
        if (data != null) {
            mData.addAll(data);
        }
    }

    @Override
    public void setFromUser(boolean fromUser) {
        mFromUser = fromUser;
    }

    @Override
    public boolean isFromUser() {
        return mFromUser;
    }

    @Override
    public void onStartLoading() {
        forceLoad();
    }

    protected List<ParcelableUser> getData() {
        return mData;
    }

    protected boolean hasId(final String id) {
        for (int i = 0; i < mData.size(); i++) {
            if (TextUtils.equals(mData.get(i).key.getId(), id)) return true;
        }
        return false;
    }

}
