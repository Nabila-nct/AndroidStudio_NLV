import { Modal, StyleSheet, Text, View } from 'react-native';


export default () =>{
    return(
         <Modal
        animationType="slide"
        transparent={true}
        visible={true}>
          <View style={styles.center}>
            <View style={styles.modalView}>
          <Text>Ola</Text>
            </View>
          </View>
        
        </Modal>
    )
}

const styles = StyleSheet.create({
   modalView: {
    backgroundColor: 'white',
    borderRadius: 6,
    padding: 20,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 5,
    },
  }
});
