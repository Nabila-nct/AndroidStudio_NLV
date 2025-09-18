import { StyleSheet, View } from 'react-native';
import { Map, Modal, Panel } from './componentes';

export default function App() {
  return (
    <View style={styles.container}>
      <Map />
      <Panel />
      <Modal />
    </View>
  );
}

const styles = StyleSheet.create({
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',

  },

  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
