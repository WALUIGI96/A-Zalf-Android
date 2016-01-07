package groep4.a_zalf.Activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import groep4.a_zalf.R;
import protocol.IBeacon;
import protocol.IBeaconListener;
import protocol.IBeaconProtocol;
import protocol.Utils;

public class Afspraken extends AppCompatActivity implements IBeaconListener {

    private Button btDiagnose;

    private static final int REQUEST_BLUETOOTH_ENABLE = 1;

    private static IBeacon beacon;
    private static IBeaconProtocol ibp;

    Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afspraken);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeUIComponents();

        btDiagnose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent diagnoseActivity = new Intent(getApplicationContext(), Diagnose.class);
                startActivity(diagnoseActivity);
            }
        });
        setUpIBeacon();
    }

    private void setUpIBeacon() {
        ibp = IBeaconProtocol.getInstance(this);
        ibp.setListener(this);
    }

    @Override
    protected void onStop() {
        ibp.stopScan();
        super.onStop();
    }

    /**
     * Call this when the phone needs to search for iBeacons.
     * @param view
     */
    public void startSearch(View view) {
        scanBeacons();
    }

    private void scanBeacons() {
        Log.i(Utils.LOG_TAG, "Scanning");

        if (!IBeaconProtocol.configureBluetoothAdapter(this)) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_BLUETOOTH_ENABLE);
        } else {
            if (ibp.isScanning()) {
                ibp.stopScan();
            }
            ibp.reset();
            ibp.startScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_BLUETOOTH_ENABLE) {
            if (resultCode == Activity.RESULT_OK) {
                scanBeacons();
            }
        }
    }

    @Override
    public void enterRegion(IBeacon ibeacon) {

    }

    @Override
    public void exitRegion(IBeacon ibeacon) {

    }

    /**
     * The point where an action can be performed when the iBeacon is found
     * our iBeacon has the following uuid:
     * "F7826DA6-4FA2-4E98-8024-BC5B71E0893E"
     * @param ibeacon The iBeacon found
     */
    @Override
    public void beaconFound(IBeacon ibeacon) {
        beacon = ibeacon;
        //TODO: THINGS WHEN BEACON FOUND
    }

    /**
     * Do things when the search has started or ended
     * @param state The state, either <code>SEARCH_STARTED</code>, <code>SEARCH_END_EMPTY</code> or <code>SEARCH_END_SUCCESS</code>
     */
    @Override
    public void searchState(int state) {
        if (state == IBeaconProtocol.SEARCH_STARTED) {
        } else if (state == IBeaconProtocol.SEARCH_END_EMPTY || state == IBeaconProtocol.SEARCH_END_SUCCESS) {
        }
    }

    /**
     * When something went wrong
     * @param status The error status code
     */
    @Override
    public void operationError(int status) {

        Toast.makeText(context, "Bluetooth error: " + status, Toast.LENGTH_SHORT).show();
    }

    private void initializeUIComponents() {
        btDiagnose = (Button) findViewById(R.id.btDiagnose);
    }

}
