package inc.iris.sih2018;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyTab extends Fragment {

    private FloatingActionButton fab;

    public MonthlyTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_monthly_tab, container, false);
        fab=view.findViewById(R.id.newMonthly);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMonthlyBooking();
            }
        });
        return view;
    }

    private void addMonthlyBooking() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

    }

}
