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
  'bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJmYTk3YmUyNC0wM2MzLTExZjAtYWM2ZS04YjVlNWI3OGY2NjQiLCJhdWQiOlsicmVzdHNlcnZpY2UiXSwidXNlcl9uYW1lIjoiY3Vvbmd0aGluaGdsMTIzQGdtYWlsLmNvbSIsInNjb3BlIjpbInJlYWQiXSwiaXNzIjoiaHR0cHM6Ly9sb2NhbGhvc3QiLCJuYW1lIjoiY3Vvbmd0aGluaGdsMTIzQGdtYWlsLmNvbSIsImV4cCI6MTc0MzY4NjI1NCwidXVpZF9hY2NvdW50IjoiZmE5N2JlMjQtMDNjMy0xMWYwLWFjNmUtOGI1ZTViNzhmNjY0IiwiYXV0aG9yaXRpZXMiOlsiVVNFUiJdLCJqdGkiOiIzODI4YjQ3Yy0yZjkyLTQwY2UtYTU3Yy02ZjNmZmI0OTljNDYiLCJjbGllbnRfaWQiOiJjbGllbnRhcHAifQ.H6lEi2ZQhxV5GDOgpWDkV8iwE2_fcMzBdugCpsWEjzRUVwCaKt7R7Y9Vp2DuHAiuoRXFEHQUnukezpj2nGgTVvJWU5CGhlglFU2-45znk9p1NqxjQTi4fzwcITukDtB_sSvlzsgfYdP7RUypAh34Ng3QogOTYsyDt8zy_B66QW5kf7bz_spfQOCuAGfF_qJxlcKGYu8JhTd5e0NMXPVp8jZIyX8WYy41BrXMut9zjHRtxEqfMY6UE1h8qOWG-tBGJSJv8SpoT3akTf9Gg4apS4hrSTOvZK_SJsCfRzkabSpfERSbSyHCZwP7D-kvzkgbywzbHrQnsAEi6lHszdOeGw';
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
