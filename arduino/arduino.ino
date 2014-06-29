void setup() {
    Serial.begin(9600);
    pinMode(12, OUTPUT);
}

int i = 0;

void loop() {
    if (Serial.available() > 0) {
        char value = Serial.read();
        if (value == 1) {
            if (i == 0) {
                digitalWrite(12, HIGH);
                i = 1;
            }
            else {
                digitalWrite(12, LOW);
                i = 0;
            }
        } else {
            digitalWrite(12, LOW);
        }
    }
    delay(1000);
}
