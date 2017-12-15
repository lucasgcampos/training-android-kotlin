package br.concrete.training.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Lucas Campos
 */

public final class Item implements Parcelable {

    private final String task;
    private final String description;

    public Item(String task, String description) {
        this.task = task;
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.task);
        dest.writeString(this.description);
    }

    protected Item(Parcel in) {
        this.task = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
