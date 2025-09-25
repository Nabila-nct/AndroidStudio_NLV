import { Image, StyleSheet, View } from 'react-native';
import { ImageBackground } from 'react-native-web';


export default function App() {
  return (
    <View style={styles.container}>
      <ImageBackground
        style={styles.fondo}
        source={{uri:'https://blog.delivered.co.kr/wp-content/uploads/2024/05/TXT-members-1-1.jpg'}}
        >

         <Image
        style={styles.foto}
        source={require('./assets/splash-icon.png')}
      />
      <Image
        style={styles.foto}
        source={{uri:'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Choi_Soo-bin_%28%EC%B5%9C%EC%88%98%EB%B9%88%29_220121.jpg/250px-Choi_Soo-bin_%28%EC%B5%9C%EC%88%98%EB%B9%88%29_220121.jpg'}}
/>
              </ImageBackground>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  foto:{
    height:100,
    width:100,
    
  }
});
