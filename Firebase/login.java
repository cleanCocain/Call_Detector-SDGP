private EditText txtemail ;
    private EditText txtpassword ;
    private Button Startlogin ;
    private FirebaseAuth auth ;
    private ProgressDialog Dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


          Dialog = new ProgressDialog(this);
          auth =  FirebaseAuth.getInstance() ;

          txtemail = (EditText) findViewById(R.id.logEmail);
          txtpassword = (EditText) findViewById(R.id.logPassword);
          Startlogin = (Button) findViewById(R.id.logLogin);


          Startlogin.setOnClickListener(this);

          if (auth.getCurrentUser()!= null){
                finish();
              startActivity(new Intent(getApplicationContext(),LoggedUser.class));
          }
    }



    @Override
    public void onClick(View v) {
        if (v == Startlogin){
            loginUser();
        }
    }

    private void loginUser() {
        String email = txtemail.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this , "Please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this , "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        Dialog.setMessage("Loging In");
        Dialog.show();

        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Dialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                                startActivity(new Intent(getApplicationContext(),LoggedUser.class));
                        }
                        Dialog.dismiss();
                    }
                });

    }
}