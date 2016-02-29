package info.nightscout.danar;

/**
 * Created by mike on 23.01.2016.
 */
import android.bluetooth.*;
import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;

import java.util.Set;

public class BluetoothDevicePreference extends ListPreference {

    public BluetoothDevicePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
        Integer size = 1;
        if (bta != null) {
            size += bta.getBondedDevices().size();
        }
        CharSequence[] entries = new CharSequence[size];
        int i = 0;
        entries[i++] = new String("VirtualPump");
        if (bta != null) {
            Set<BluetoothDevice> pairedDevices = bta.getBondedDevices();
            for (BluetoothDevice dev : pairedDevices) {
                entries[i] = dev.getName();
                i++;
            }
        }
        setEntries(entries);
        setEntryValues(entries);
    }

    public BluetoothDevicePreference(Context context) {
        this(context, null);
    }

}