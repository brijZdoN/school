package beyou.school.STUDENT;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beyou.school.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StuResult extends Fragment {


    public StuResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_stu_result, container, false );
    }

}
