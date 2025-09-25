import { StyleSheet, Text, View } from 'react-native';

const Texto = props => {
  const {children} = props 
    return(
      <Text>{children}</Text>
    ) 
  }

  export default function App() {
  return (
    <View style={styles.container}>
      <Texto titulo={'Hola 1'}/>
      <Texto titulo={'Hola 2'}/>
      <Texto titulo={'Hola 3'}/>
      <Texto titulo={'Hola 4'}/>


      <Text>Hola jiji</Text>
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
});
