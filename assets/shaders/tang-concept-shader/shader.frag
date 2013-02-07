varying vec3 normal, lightDir;
varying vec2 texCoord;
varying vec3 color;
uniform sampler2D texture;
void main() {
	float intensity;
	vec3 n;
	vec4 _color;
	n = normalize(normal);
	intensity = dot(lightDir, n);
	if(intensity > 0.5) {
		_color = vec4(color ,1.0);
	} else if(intensity > 0.2) {
		_color = vec4(color * 0.8,1.0);
	} else if(intensity > 0.1) {
		_color = vec4(color * 0.4,1.0);
	} else {	
		_color = vec4(color * 0.2,1.0);         
	}

	_color = vec4(color * intensity * 2.0,1.0);


	gl_FragColor = _color * texture2D(texture, texCoord);   
}