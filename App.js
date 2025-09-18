import { useEffect, useState } from 'react';
import { ActivityIndicator, FlatList, StyleSheet, Text, View } from 'react-native';

export default function App() {
  const [users, setUsers]= useState([])
  const[loading, setLoading]=useState(true)

  useEffect(()=>{
    fetch('https://jsonplaceholder.typicode.com/todos')
    .then(response=>response.json())
    .then(data=> {
      setUsers(data)
      setLoading(false)
    })
  },[])

if(loading){
  return(<View style={styles.center}> <Text >Cargando...</Text> 
 <ActivityIndicator size ='large' color= '#00f'/>
  </View> )
}

  return (
    <View style={styles.container}>
      <Text>Consumiendo una API</Text>
      <FlatList  
        data={users}
        renderItem={({item})=><View style={styles.item}>
          <Text>{item.title}</Text>
          <Text>{item.completed ? "✅" : "❌"}</Text>
        </View>}
        keyExtractor={item=>String(item.id)}
      />

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
  center:{
    flex:1,
    backgroundColor: '#ffffffff',
    alignItems:'center',
    justifyContent:'center',

  }, 
  item:{
    flexDirection:'row',
    justifyContent:'space-between',
    backgroundColor:"#a2d2ff", 
    padding:20,
    marginVertical:8, 
    marginHorizontal:16,  
    borderRadius:10,
  }
});