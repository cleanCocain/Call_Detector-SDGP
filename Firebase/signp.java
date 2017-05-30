private EditText txtemail ;
    private EditText txtpassword;
    private EditText txtconfirmpassword ;
    private Button signUp;
    private ProgressDialog Dialog ;
    private FirebaseAuth auth ;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

          auth =  FirebaseAuth.getInstance();
          Dialog = new ProgressDialog(this);


          txtemail = (EditText) findViewById(R.id.email);
          txtpassword = (EditText) findViewById(R.id.password);
          txtconfirmpassword = (EditText) findViewById(R.id.confirmpassword);
          signUp = (Button) findViewById(R.id.signup);

          signUp.setOnClickListener(this);

        if (auth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoggedUser.class));
        }

    }


    @Override
    public void onClick(View v) {

        if (v == signUp){
            register ();
        }
    }

    private void register() {
        String email = txtemail.getText().toString().trim();
        String password = txtpassword.getText().toString().trim();
        String Cpassword = txtconfirmpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this , "Please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this , "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Cpassword)){
            Toast.makeText(this , "Please enter the password again", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Cpassword.equals(password)){
            Toast.makeText(this , "Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        Dialog.setMessage("Registering......");
        Dialog.show();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}