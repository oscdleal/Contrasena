package net.oscardl.contrasena;


import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

	TextView txtcontrasena;
	Button btogenerar;
	Button btocopiar;
	Button btoopciones;
	
	int longitud = 4;
	int grupos = 1;
	boolean letras = true;
	boolean numeros = true;
	boolean mayusculas = true;
	boolean simbolos = true;
	
	
	public String GeneraCodigos (int grupos, int longitud, Boolean letras, boolean numeros, boolean mayusculas, boolean simbolos)
	{
		
		
		char codletras[] = {'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
		char codnumeros[] = {'1','2','3','4','5','6','7','8','9','0'};
		char codmayusculas[] = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
		char codsimbolos[] = {'-','+','=','_',';',':','?','¿','*','$','#','@'};
		
		StringBuilder sb = new StringBuilder();
		
		if(letras)sb.append(codletras);
		if(numeros)sb.append(codnumeros);
		if(mayusculas)sb.append(codmayusculas);
		if(simbolos)sb.append(codsimbolos);
		
		char codigos[] = sb.toString().toCharArray();
		
				
		String enigma = "";
		String resultado = "";
		
		for (int i = 1; i <= grupos; i++) {
			enigma = "";
			for (int j = 0; j < longitud; j++) {
				enigma += codigos[(int)Math.floor(Math.random()*codigos.length)];	
			}
			if(i == grupos){
			resultado += enigma;
			}else{
				resultado += enigma+" ";
			}
		}
		
		return resultado;
	}
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void copiaPortaPapeles(String texto){
		
		 int sdk = android.os.Build.VERSION.SDK_INT;
         if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
             android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
             clipboard.setText(texto);
         } else {
             android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
             android.content.ClipData clip = android.content.ClipData.newPlainText("text label",texto);
             clipboard.setPrimaryClip(clip);
         }
	   
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtcontrasena = (TextView) findViewById(R.id.Txt_Contrasena);
		btogenerar = (Button) findViewById(R.id.Bto_Generar);
		btocopiar = (Button) findViewById(R.id.Bto_Copiar);
		btoopciones = (Button) findViewById(R.id.Bto_Opciones);
		
		txtcontrasena.setOnClickListener(this);
		btogenerar.setOnClickListener(this);
		btocopiar.setOnClickListener(this);
		btoopciones.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.Bto_Generar:
			String clave = GeneraCodigos(grupos, longitud,letras,numeros,mayusculas,simbolos);
			txtcontrasena.setText(clave);
			//Toast.makeText(this, clave, Toast.LENGTH_LONG).show();
			
			
			break;
		
		case R.id.Bto_Copiar:
		//	String claveEnvia = txtcontrasena.getText().toString();
			//Intent intento = new Intent("android.intent.action.SEGUNDO");
			//intento.putExtra("EnviaClave", claveEnvia);
			//startActivity(intento);
			
			String clipi = txtcontrasena.getText().toString();			
			copiaPortaPapeles(clipi);
			
			Toast.makeText(this, R.string.mensajecopia, Toast.LENGTH_LONG).show();
	        
			break;
		case R.id.Bto_Opciones:
			Intent intento = new Intent("android.intent.action.OPCIONES");
			startActivity(intento);
			break;
			
		default:
			break;
		}
		
	}

}
