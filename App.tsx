import {
  PermissionsAndroid,
  Pressable,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import React, {useEffect} from 'react';
import nativeCalc from './specs/NativeCalc';

const accessToken =
  'bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYTk3YmUyNC0wM2MzLTExZjAtYWM2ZS04YjVlNWI3OGY2NjQiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoiY3Vvbmd0aGluaGdsMTIzQGdtYWlsLmNvbSIsInNjb3BlIjpbInJlYWQiXSwiaXNzIjoiaHR0cHM6Ly9sb2NhbGhvc3QiLCJuYW1lIjoiY3Vvbmd0aGluaGdsMTIzQGdtYWlsLmNvbSIsImV4cCI6MTc0MzczMDI5NiwidXVpZF9hY2NvdW50IjoiZmE5N2JlMjQtMDNjMy0xMWYwLWFjNmUtOGI1ZTViNzhmNjY0IiwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiI4NzEyYzAwYi00Y2UzLTQzY2ItOTMxYy05MTU5OWFlOGZiZWIiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAifQ.IL1T5fJNNkn2BLG_yo51PUpHMqSkBXUwwkP8-xGkChXnjSgAFfrCcK_swrlOpIas4mnEiptdqO1hzHopGSPo8KPNr4f5GfvuaHzkFaHnf7etkY_DV7zMxHY6tSMHC2uNvpjtM7UqbuzGYUTVflr_9rltASCsD6cMpB2QDCGeGJXjItd1So4l9CMxwYnxf_DkE_XY6KXVmdO4ZaQCIPg7d8ibrbyJ3PCNAY--Bmt85VHP5SZESEAmdnxzX_UKIqvaXajFXmGnRBTd7FW_M1cN4v7phI6-RZ2wwPzSF1Zs43qbB358opNQ8HtHOMEtsLnoRS7moUjI-7Npd0O0ceENwg';
const tokenId = '309930b8-63f0-12b4-e063-63199f0aebcf';
const tokenKey =
  'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJMZp3Y98o7OAILLBCxSOGPRxmjX7Vzw5iIvVkDYuEgsGwB04CPjVZNtBR+HXSFokYeyWkQN5KhkABVq8024380CAwEAAQ==';

const App = () => {
  const requestCamera = async () => {
    const granted = await PermissionsAndroid.request(
      PermissionsAndroid.PERMISSIONS.CAMERA,
      {
        title: 'Cool Photo App Camera Permission',
        message:
          'Cool Photo App needs access to your camera ' +
          'so you can take awesome pictures.',
        buttonNeutral: 'Ask Me Later',
        buttonNegative: 'Cancel',
        buttonPositive: 'OK',
      },
    );

    console.log(granted);
  };

  useEffect(() => {
    requestCamera();
  }, []);

  const handleStartEkyc = async () => {
    try {
      const res = await nativeCalc.startEkyc(accessToken, tokenId, tokenKey);

      console.log(res);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <View style={styles.container}>
      <Pressable onPress={handleStartEkyc} style={{padding: 10}}>
        <Text>Start EKYC</Text>
      </Pressable>
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
