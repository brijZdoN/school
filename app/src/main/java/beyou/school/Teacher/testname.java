package beyou.school.Teacher;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import beyou.school.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class testname extends Fragment {

    EditText editText;
    Button btn;
    Spinner spinner;
    String[] classlist={"1","2","3","4","5"};
    String cname;
    public testname(

    ) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_testname, container, false);

        editText=v.findViewById(R.id.testid);
      /* spinner=v.findViewById(R.id.spinner);
         ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,classlist);
         spinner.setAdapter(adapter);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               Toast.makeText(getContext(), classlist[i], Toast.LENGTH_SHORT).show();
               cname=classlist[i];
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });*/
        btn=v.findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             String s=editText.getText().toString().trim();
             if(s.isEmpty())
             {
                 Toast.makeText(getContext(), "Please enter test name first", Toast.LENGTH_SHORT).show();
             }
             else
             {
                 Bundle bundle=new Bundle();
                 bundle.putString("name",s);
                 bundle.putString( "type","1" );
                 queslist ques=new queslist();
                 ques.setArguments(bundle);
                 InputMethodManager manager=(InputMethodManager)getActivity()
                                              .getSystemService( Context.INPUT_METHOD_SERVICE);
                                               manager
                                              .hideSoftInputFromWindow(getActivity().getCurrentFocus()
                                              .getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS  );


                                               getActivity()
                                              .getSupportFragmentManager()
                                              .beginTransaction()
                                              .add(R.id.testactivityid,ques,queslist.class.getSimpleName())
                                              .addToBackStack(null)
                                              .commit();

             }

            }
        });
        return v;
    }

}
